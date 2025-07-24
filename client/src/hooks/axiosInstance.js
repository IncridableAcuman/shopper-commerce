import axios from 'axios';

const axiosInstance=axios.create({
    withCredentials:true,
    baseURL:"http://localhost:8080/api"
});

axiosInstance.interceptors.request.use(

);

axiosInstance.interceptors.response.use(

);


export default axiosInstance;