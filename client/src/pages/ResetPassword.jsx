import { Lock, Mail, Send } from 'lucide-react'

const ResetPassword = () => {
  return (
    <>
    <div className="w-full h-screen flex items-center justify-center bg-gray-100">
      <div className="w-full max-w-md p-5 rounded-lg shadow-md">
        <h1 className='text-3xl font-semibold pb-4 text-green-600'>Reset Password</h1>
        <form className='space-y-4'>
          <div className="flex items-center gap-2 border border-green-500 p-3 rounded placeholder:text-sm">
            <Lock size={20} className='text-green-500' />
            <input type="password" placeholder='Password' className='outline-none w-full' />
          </div>
          <div className="flex items-center gap-2 border border-green-500 p-3 rounded placeholder:text-sm">
            <Lock size={20} className='text-green-500' />
            <input type="password" placeholder='Confirm Password' className='outline-none w-full' />
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