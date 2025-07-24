import axios from 'axios';
import { toast } from 'react-toastify'
const axiosInstance=axios.create({
    withCredentials:true,
    baseURL:"http://localhost:8080/api"
});

axiosInstance.interceptors.request.use(
config=>{
    const accessToken=localStorage.getItem("accessToken");
    if(accessToken){
        config.headers['Authorization']=`Bearer ${accessToken}`;
    }
    return config;
},
(error)=>Promise.reject(error)
);

axiosInstance.interceptors.response.use(
config=>config,
async (error)=>{
    const originalRequest=error.config;
    if(error?.response?.status==401 && !originalRequest._retry){
        originalRequest._retry=true;
        try {
            const {data}=await axiosInstance.get("/auth/refresh");
                localStorage.setItem("accessToken",data.accessToken);
                originalRequest.headers['Authorization']=`Bearer ${data.accessToken}`;
                return axiosInstance(originalRequest);
            
        } catch (error) {
            console.log(error);
            localStorage.removeItem("accessTokwn");
            toast.error(error?.response?.message || error?.message | "Something went wrong");
            window.location.href="/login"
        }
    }
},
(error)=>Promise.reject(error)
);


export default axiosInstance;