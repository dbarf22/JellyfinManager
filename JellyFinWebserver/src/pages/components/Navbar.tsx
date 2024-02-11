import Head from "next/head";
import Link from "next/link";
import { MdOutlineCast } from "react-icons/md";


export default function Navbar() {
  return (
    <>
    <header className=" w-full bg-indigo-950 shadow top-0">
      <div className=" flex justify-center">
        <h1 className=" text-neutral-100 text-4xl py-4 px-4 bg-gradient-to-r from-sky-500 via-purple-400 to-pink-400 inline-block text-transparent bg-clip-text"> Jellyfin Manager </h1>
      </div>
      
      <div className=" flex justify-between mx-48 px-4">
        <div className=" items-center flex gap-5 py-5">
         <button className=" rounded-lg bg-indigo-500 hover:shadow-lg hover:bg-indigo-800 px-4">
           <p className=" text-neutral-100 text-2xl py-2 hover:text-indigo-400 "> Library </p>
          </button>

         <button className=" rounded-lg bg-indigo-500 hover:shadow-lg hover:bg-indigo-800 px-4">
           <p className=" text-neutral-100 text-2xl py-2 hover:text-indigo-400 "> Queue </p>
         </button>
        </div>  
      
        <div className=" flex justify-end">
         <button className="">
            <MdOutlineCast className=" text-4xl text-neutral-100 hover:text-indigo-400"/>
         </button>
        </div>
      </div> 
      <hr></hr>
    </header>
    </>
  );
}
