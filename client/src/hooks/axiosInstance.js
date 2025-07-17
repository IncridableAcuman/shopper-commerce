import axios from 'axios';

const axiosInstance=axios.create({
    withCredentials:true,
    baseURL:"http://localhost:8080/api"
});


export default axiosInstance;