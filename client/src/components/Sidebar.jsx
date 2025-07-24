import { Info, Menu, X } from 'lucide-react';
import React, { useState } from 'react'
import { Link } from 'react-router-dom';

const Sidebar = () => {
    const [isOpen,setIsOpen]=useState(false);

    const data=[
        {name:"Products",icon:<Info size={20} />,path:"/admin/products"},
        {name:"Create",icon:<Info size={20} />,path:"/admin/create"}
    ]

  return (
    <div className='flex'>
        <div className={`fixed top-0 left-0 h-full bg-gray-900 text-white w-64  
        transform ${isOpen? "translate-x-0":"-translate-x-64"} 
        transition-transform duration-300 ease-in-out lg:translate-x-0`}>
            <div className="flex items-center justify-between p-4 border-b border-gray-700">
                <h1 className='text-lg font-semibold'>Dashboard</h1>
                {isOpen && (
                    <button className='lg:hidden text-gray-300 hover:text-white'
                     onClick={()=>setIsOpen(false)}>
                        <X size={24} />
                    </button>
                )}
            </div>
            {/* nav */}
            <div className="mt-4">
                {
                    data.map((item,index)=>(
                        <Link key={index} to={item.path}
                         className='flex items-center gap-3 px-4 py-3
                          text-gray-300 hover:bg-gray-800 hover:text-white transition duration-300 rounded-lg'>
                            {item.icon}
                            {item.name}
                        </Link>
                    ))
                }
            </div>
              {/* nav */}
        </div>
        {/* toggle */}
        {!isOpen && (
            <button className='lg:hidden fixed top-4 left-4 bg-gray-900 text-white p-2'
             onClick={()=>setIsOpen(true)}>
                <Menu size={24} />
            </button>
        )}
    </div>
  )
}

export default Sidebar