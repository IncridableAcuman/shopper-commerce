import { ShoppingBag, ShoppingBasket } from 'lucide-react'
import { useNavigate } from 'react-router-dom'

const Navbar = () => {
    const navigate=useNavigate();
  return (
    <>
    <header className='fixed w-full left-0 top-0 flex items-center justify-between p-4 sm:px-6 md:px-8 lg:px-10 bg-gray-900 opacity-95'>
        <img src="./logo.png" alt="logo" className='w-12 cursor-pointer' />
        <div className="flex items-center gap-4 sm:gap-6 md:gap-8 lg:gap-10">
              <a href="#" className='hover:text-sky-700 transition duration-300'>Home</a>
              <a href="#" className='hover:text-sky-700 transition duration-300'>Men</a>
              <a href="#" className='hover:text-sky-700 transition duration-300'>Women</a>
              <a href="#" className='hover:text-sky-700 transition duration-300'>Kids</a>
          </div>
          <div className="flex items-center gap-4 sm:gap-6 md:gap-8 lg:gap-10">
            <button className='border border-gray-300 px-5 py-2 cursor-pointer rounded shadow
             hover:bg-gray-300 hover:text-gray-900 transition duration-300' onClick={()=>navigate("/login")}>Login</button>
            <ShoppingBasket/>
          </div>
      </header>
    </>
  )
}

export default Navbar