import { Lock, Mail, Send } from 'lucide-react'
import { useNavigate } from 'react-router-dom'

const Login = () => {
  const navigate=useNavigate();
  return (
    <>
    <div className="w-full h-screen flex items-center justify-center bg-gray-100">
      <div className="w-full max-w-md p-5 rounded-lg shadow-md">
        <h1 className='text-3xl font-semibold pb-4 text-green-600'>Sign In</h1>
        <form className='space-y-4'>
          <div className="flex items-center gap-2 border border-green-500 p-3 rounded placeholder:text-sm">
            <Mail size={20} className='text-green-500' />
            <input type="email" placeholder='Your email' className='outline-none w-full' />
          </div>
          <div className="flex items-center gap-2 border border-green-500 p-3 rounded placeholder:text-sm">
            <Lock size={20} className='text-green-500' />
            <input type="password" placeholder='password' className='outline-none w-full' />
          </div>
            <p className='text-sm text-gray-400 cursor-pointer hover:text-gray-500 transition duration-300'
             onClick={()=>navigate("/forgot-password")} >Forgot Password</p>
          <div className="flex items-center justify-center gap-3 cursor-pointer bg-green-500 p-2.5 text-white rounded-md shadow hover:bg-green-600 transition duration-300">
            <Send size={20} />
            <button>Sign In</button>
          </div>
        </form>
        <div className="pt-3 text-sm text-gray-400">
          <p>Don't have an account? <span className='text-gray-500 cursor-pointer hover:underline'
           onClick={()=>navigate("/register")}>Sign Up</span></p>
        </div>
      </div>
    </div>
    </>
  )
}

export default Login