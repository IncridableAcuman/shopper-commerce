import { Mail, Send } from 'lucide-react'
import { useState } from 'react';
import axiosInstance from '../hooks/axiosInstance';
import { toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';

const ForgotPassword = () => {
  const [email,setEmail]=useState('');
  const navigate=useNavigate();

    if(localStorage.getItem("accessToken")){
    navigate("/")
  }

  const handleSubmit=async (e)=>{
    e.preventDefault();
    try {
      const {data}=await axiosInstance.post("/auth/forgot-password",{email});
      toast.success(data?.message || "Reset password link sent to email");
    } catch (error) {
      console.log(error);
      toast.error(error?.response?.message || error?.message || "Forgot password failed!");
    }
  }
  return (
    <>
    <div className="w-full h-screen flex items-center justify-center bg-gray-900 text-white">
      <div className="w-full max-w-md p-5 rounded-lg shadow-md border border-green-400">
        <h1 className='text-3xl font-semibold pb-4 text-green-600'>Forgot Password</h1>
        <form className='space-y-4' onSubmit={handleSubmit}>
          <div className="flex items-center gap-2 border border-green-500 p-3 rounded placeholder:text-sm">
            <Mail size={20} className='text-green-500' />
            <input type="email" placeholder='Your email' className='outline-none w-full' 
            value={email} onChange={(e)=>setEmail(e.target.value)} />
          </div>
          <div className="flex items-center justify-center gap-3 cursor-pointer bg-green-500 p-2.5 text-white rounded-md shadow hover:bg-green-600 transition duration-300">
            <Send size={20} />
            <button>Forgot Password</button>
          </div>
        </form>
      </div>
    </div>
    </>
  )
}

export default ForgotPassword