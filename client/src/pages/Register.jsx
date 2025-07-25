import { Lock, Mail, Send, User } from 'lucide-react'
import { useState } from 'react';
import { useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import axiosInstance from '../hooks/axiosInstance';

const Register = () => {
  const [username,setUsername]=useState('');
  const [email,setEmail]=useState('');
  const [password,setPassword]=useState('');
  const navigate=useNavigate();

  if(localStorage.getItem("accessToken")){
    navigate("/")
  }

  const handleSubmit=async (e)=>{
    e.preventDefault();
    try {
      const {data}=await axiosInstance.post("/auth/register",{username,email,password});
      localStorage.setItem("accessToken",data.accessToken);
      localStorage.setItem("role",data?.role)
      toast.success(data?.message || "Successfully");
      navigate("/")
    } catch (error) {
      console.log(error);
      toast.error(error?.response?.message || error?.message || "Registration failed!");
    }
  }
  
  return (
    <>
    <div className="w-full h-screen flex items-center justify-center bg-gray-900 text-white">
      <div className="w-full max-w-md p-5 rounded-lg shadow-md border border-green-400">
        <h1 className='text-3xl font-semibold pb-4 text-green-600'>Sign Up</h1>
        <form className='space-y-4' onSubmit={handleSubmit}>
          <div className="flex items-center gap-2 border border-green-500 p-3 rounded placeholder:text-sm">
            <User size={20} className='text-green-500' />
            <input type="text" placeholder='Username' className='outline-none w-full'
             value={username} onChange={(e)=>setUsername(e.target.value)} />
          </div>
          <div className="flex items-center gap-2 border border-green-500 p-3 rounded placeholder:text-sm">
            <Mail size={20} className='text-green-500' />
            <input type="email" placeholder='Your email' className='outline-none w-full' 
            value={email} onChange={(e)=>setEmail(e.target.value)} />
          </div>
          <div className="flex items-center gap-2 border border-green-500 p-3 rounded placeholder:text-sm">
            <Lock size={20} className='text-green-500' />
            <input type="password" placeholder='password' className='outline-none w-full' 
            value={password} onChange={(e)=>setPassword(e.target.value)} />
          </div>
            <p className='text-sm text-gray-400 cursor-pointer hover:text-gray-500 transition duration-300'
             onClick={()=>navigate("/forgot-password")} >Forgot Password</p>
          <div className="flex items-center justify-center gap-3 cursor-pointer bg-green-500 p-2.5 text-white rounded-md shadow hover:bg-green-600 transition duration-300">
            <Send size={20} />
            <button>Sign Up</button>
          </div>
        </form>
        <div className="pt-3 text-sm text-gray-400">
          <p>Already have an account? <span className='text-gray-500 cursor-pointer hover:underline'
           onClick={()=>navigate("/login")}>Sign In</span></p>
        </div>
      </div>
    </div>
    </>
  )
}

export default Register