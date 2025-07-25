import React from 'react'
import Navbar from '../components/Navbar'
import { useNavigate } from 'react-router-dom'

const Home = () => {
  const navigate=useNavigate();

    if(!localStorage.getItem("accessToken")){
    navigate("/landing")
  }

  return (
    <>
    <div className="bg-gray-900 text-white w-full min-h-screen">
      <Navbar/>
      {/* data */}
      <div className="">
        <img src="./banner_mens.png" alt="banner" className='pt-26 w-full max-w-7xl mx-auto rounded' />
        <div className="flex flex-col lg:flex-row items-center justify-between pdg">
          <img src="./hero_image.png" alt="hero image" className='w-1/3' />
          <div className="">
            <h2 className='text-4xl font-semibold uppercase'>New collections for everyone</h2>
            <p className='text-sm pt-3 text-gray-400'>Lorem ipsum dolor sit amet consectetur adipisicing elit.
              <br />  Ut et nostrum iure!</p>
            <div className="flex items-center gap-3 pt-4">
              <button className='border border-green-400 px-5 py-2.5 cursor-pointer shadow
               hover:bg-green-400 hover:text-gray-900 transition duration-300'>Read more</button>
              <button className='bg-green-400 text-gray-900 px-5 py-2.5 shadow cursor-pointer
               hover:bg-gray-900 hover:text-white hover:border border-green-400 transition duration-300'>Collections</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    </>
  )
}

export default Home