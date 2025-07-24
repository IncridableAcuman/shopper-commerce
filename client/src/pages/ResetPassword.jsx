import { Lock, Mail, Send } from 'lucide-react'
import { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom'
import { toast } from 'react-toastify';
import axiosInstance from '../hooks/axiosInstance';

const ResetPassword = () => {
  const {refreshToken}=useParams();
  const [password,setPassword]=useState('');
  const [confirmPassword,setConfirmPassword]=useState('');
  const navigate=useNavigate();

  const handleSubmit=async (e)=>{
    e.preventDefault();
    if(password!==confirmPassword){
      toast.info("Password must be equal");
      navigate("/login");
    }
    try {
      await axiosInstance.put("/auth/reset-password",{refreshToken,password});
      toast.success("Password updated successfully");
    } catch (error) {
      console.log(error);
      toast.error(error?.response?.message || error?.message || "Forgot password failed!");
    }
  }

  return (
    <>
    <div className="w-full h-screen flex items-center justify-center bg-gray-900 text-white">
      <div className="w-full max-w-md p-5 rounded-lg shadow-md border border-green-400">
        <h1 className='text-3xl font-semibold pb-4 text-green-600'>Reset Password</h1>
        <form className='space-y-4' onSubmit={handleSubmit}>
          <div className="flex items-center gap-2 border border-green-500 p-3 rounded placeholder:text-sm">
            <Lock size={20} className='text-green-500' />
            <input type="password" placeholder='Password' className='outline-none w-full' 
            value={password} onChange={(e)=>setPassword(e.target.value)} />
          </div>
          <div className="flex items-center gap-2 border border-green-500 p-3 rounded placeholder:text-sm">
            <Lock size={20} className='text-green-500' />
            <input type="password" placeholder='Confirm Password' className='outline-none w-full' 
            value={confirmPassword} onChange={(e)=>setConfirmPassword(e.target.value)} />
          </div>
          <div className="flex items-center justify-center gap-3 cursor-pointer bg-green-500 p-2.5 text-white rounded-md shadow hover:bg-green-600 transition duration-300">
            <Send size={20} />
            <button>Reset Password</button>
          </div>
        </form>
      </div>
    </div>
    </>
  )
}

export default ResetPassword