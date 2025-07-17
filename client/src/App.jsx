import React from 'react'
import { Route, Routes } from 'react-router-dom'
import Landing from './pages/Landing'
import Register from './pages/Register'
import Login from './pages/Login'
import ForgotPassword from './pages/ForgotPassword'
import ResetPassword from './pages/ResetPassword'
import Home from './pages/Home'
import Cart from './pages/Cart'
import KidsPage from './pages/KidsPage'
import MenPage from './pages/MenPage'
import WomenPage from './pages/WomenPage'
import ProductData from './pages/ProductData'
import Payment from './pages/Payment'

const App = () => {
  return (
    <>
    <Routes>
      <Route path='/landing' element={<Landing/>} />
      <Route path='/register' element={<Register/>} />
      <Route path='/login' element={<Login/>} />
      <Route path='/forgot-password' element={<ForgotPassword/>} />
      <Route path='/reset-password' element={<ResetPassword/>} />
      <Route path='/' element={<Home/>} />
      <Route path='/cart' element={<Cart/>} />
      <Route path='/kids' element={<KidsPage/>} />
      <Route path='/men' element={<MenPage/>} />
      <Route path='/women' element={<WomenPage/>} />
      <Route path='/data/:id' element={<ProductData/>} />
      <Route path='/payment' element={<Payment/>}  />
    </Routes>
    </>
  )
}

export default App