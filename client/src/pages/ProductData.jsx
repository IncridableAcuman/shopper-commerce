import React from 'react'
import Navbar from '../components/Navbar'

const ProductData = () => {
  return (
    <>
    <div className="bg-gray-900 text-white w-full h-screen">
      <Navbar/>
      {/* product data */}
      <div className="flex items-center justify-between pdg">
        <div className="">
          <img src="./p1_product_i1.png" alt="p1_product_i1.png" />
        </div>
      </div>
    </div>
    </>
  )
}

export default ProductData