import React from 'react'

const Create = () => {
  return (
    <>
    <div className="w-full max-w-md  p-4 sm:p-6 md:p-8 lg:p-10 space-y-4">
            <div className="border border-gray-700 p-2 rounded">
                <input type="text" placeholder='Title' className='outline-none w-full' />
            </div>
        <div  className="border border-gray-700 p-2 rounded">
            <textarea  className='outline-none w-full' placeholder='Description' ></textarea>
        </div>
        <div className="border border-gray-700 p-2 rounded">
            <input type="number"  className='outline-none w-full' placeholder='Price'  />
        </div>
        <div  className="border border-gray-700 p-2 rounded">
            <input type="number"  className='outline-none w-full' placeholder='Quantity'  />
        </div>
        <div className="border border-gray-700 p-2 rounded">
            <input type="text"  className='outline-none w-full' placeholder='Image'  />
        </div>
        <div className="">
            <select className='border border-gray-700 p-2 rounded'>
                <option value="">MEN</option>
                <option value="">WOMEN</option>
                <option value="">KIDS</option>
            </select>
        </div>
        <div className="border border-gray-700 p-2 rounded">
            <input type="text" placeholder='Sizes' className='outline-none w-full' />
        </div>
        <button className='bg-gray-900 text-white px-5 py-2 rounded shadow cursor-pointer hover:bg-gray-700 transition duration-300'>Create product</button>
    </div>
    </>
  )
}

export default Create