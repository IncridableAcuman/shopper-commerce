import { createContext, useState } from "react"
import axiosInstance from "../hooks/axiosInstance";

const AuthContext=createContext();

const AuthProvider = ({children}) => {
  const [user,setUser]=useState(null);

  const userData=async ()=>{
    try {
      const {data}=await axiosInstance.get("/auth/user-data");
      setUser(data);
    } catch (error) {
      console.log(error);
    }
  }

  return (
    <>
    <AuthContext.Provider value={{user,userData}}>
      {children}
    </AuthContext.Provider>
    </>
  )
}

export {AuthContext,AuthProvider}