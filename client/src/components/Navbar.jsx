import { ShoppingBasket } from 'lucide-react'
import { useNavigate,Link } from 'react-router-dom'

const Navbar = () => {
    const navigate=useNavigate();
  return (
    <>
    <header className='fixed w-full left-0 top-0 flex items-center justify-between p-4 sm:px-6 md:px-8 lg:px-10 bg-gray-900 opacity-95'>
        <img src="./logo.png" alt="logo" className='w-12 cursor-pointer' />
        <div className="flex items-center gap-4 sm:gap-6 md:gap-8 lg:gap-10">
              <Link to={"/"} className='hover:text-sky-700 transition duration-300'>Home</Link>
              <Link to={"/men"} className='hover:text-sky-700 transition duration-300'>Men</Link>
              <Link to={"/women"} className='hover:text-sky-700 transition duration-300'>Women</Link>
              <Link to={"/kids"} className='hover:text-sky-700 transition duration-300'>Kids</Link>
              <Link to={"/admin"} className='hover:text-sky-700 transition duration-300'>Admin</Link>
          </div>
          <div className="flex items-center gap-4 sm:gap-6 md:gap-8 lg:gap-10">
            <button className='border border-gray-300 px-5 py-2 cursor-pointer rounded shadow
             hover:bg-gray-300 hover:text-gray-900 transition duration-300' onClick={()=>navigate("/login")}>Login</button>
            <ShoppingBasket 
            className='cursor-pointer text-gray-400
             hover:text-gray-600 transition duration-300'
              onClick={()=>navigate("/cart")}/>
          </div>
      </header>
    </>
  )
}

export default Navbar