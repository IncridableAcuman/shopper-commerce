import React from 'react'
import Navbar from '../components/Navbar'
import Footer from '../components/Footer'

const WomenPage = () => {
      const data=[
    {
        id:1,
        name:"Striped Flutter Sleeve Overlap Collar Peplum Hem Blouse",
        image:"./p1_product_i1.png",
        new_price:50.00,
        old_price:80.50,
      },
      {id:2,
        name:"Striped Flutter Sleeve Overlap Collar Peplum Hem Blouse",
        image:"./p1_product_i2.png",
        new_price:85.00,
        old_price:120.50,
      },
      {id:3,
        name:"Striped Flutter Sleeve Overlap Collar Peplum Hem Blouse",
        image:"./p1_product_i3.png",
        new_price:60.00,
        old_price:100.50,
      },
      {id:4,
        name:"Striped Flutter Sleeve Overlap Collar Peplum Hem Blouse",
        image:"./p1_product_i4.png",
        new_price:100.00,
        old_price:150.00,
      },
  ]
  return (
    <>
    <div className="bg-gray-900 text-white w-full min-h-screen">
      <Navbar/>
      <div className="pdg">
        <img src="./banner_women.png" alt="banner" className='pt-20 rounded' />
        <div className="flex items-center justify-between p-4">
          <p className='text-sm text-gray-400'>Products</p>
          <select name="" id="" className='border border-gray-400 rounded-full cursor-pointer px-2 bg-gray-900 text-white'>
            <option value="" className=''>Sort</option>
            <option value="">Price</option>
          </select>
        </div>
        {/* products */}
        <div className="">
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
          {
            data.map((item)=>(
              <div className="bg-gray-800 shadow p-3 space-y-1.5" key={item.id}>
                <img src={item.image} alt={item.name} className='w-full rounded' />
                <h2 className='font-semibold'>{item.name}</h2>
                <div className="flex items-center justify-between px-2">
                  <p>{item.new_price}$</p>
                   <p className=''>{item.old_price}$</p>
                </div>
              </div>
            ))
          }
        </div>
        {/* btn */}
        <button className='flex items-center justify-center mx-auto mt-4 border border-gray-300 px-5 py-2
         cursor-pointer hover:bg-gray-300 hover:text-gray-900 transition duration-300'>Show more</button>
        </div>
      </div>
      {/* banner */}
      <div className="w-full max-w-7xl mx-auto my-10 p-4">
        <img src="./woman-c.jpg" alt="woman" className='rounded' />
      </div>
      {/* footer */}
      <Footer/>
    </div>
    </>
  )
}

export default WomenPage