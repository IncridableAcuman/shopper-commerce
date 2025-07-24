import React from 'react'
import Sidebar from '../components/Sidebar'
import { Outlet } from 'react-router-dom'

const Admin = () => {
  return (
    <div className='flex min-h-screen'>
        <Sidebar/>
        <main className='flex-1 lg:ml-64 p-10 sm:p-8 md:p-6 lg:p-4'>
            <Outlet/>
        </main>
    </div>
  )
}

export default Admin