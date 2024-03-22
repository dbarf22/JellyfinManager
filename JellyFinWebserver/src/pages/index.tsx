/* eslint-disable @typescript-eslint/no-unsafe-assignment */
/* eslint-disable @typescript-eslint/no-unsafe-call */
/* eslint-disable @typescript-eslint/no-unsafe-return */
import Head from "next/head";
import Link from "next/link";
import Navbar from "./components/Navbar";
import Queue from "./components/Queue";
import Library from "./components/Library";
import { useState } from "react";
import { MdOutlineCast } from "react-icons/md";



export default function Home() {
  const [navbar, setNavbar] = useState(false);

  const playQueue = async (session: string) => {
    try {
        // TODO Replace With variable for the Java Server IP/route
        const apiUrl = "http://172.20.75.133:8080/queue/sendQueue";
        const requestBody = session.toString();
        console.log(session);
    
        const res = await fetch(apiUrl, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: requestBody,
        });
        console.log(res);

        if (res.ok) {
            console.log(`yay`);
        } else {
            console.log(`Fwomp womp`);
        }
    } catch (err) {
        console.log(err);
    }
};
  return (
    <>
      <Head>
        
        <title>Jellyfin Queue</title>
        <meta name="description" content="Generated by create-t3-app" />
        <link rel="icon" href="/favicon.ico" />
        
      </Head>
      
      <main className=" font-sans">
      <header className=" w-full bg-indigo-950 shadow top-0">
      <div className=" flex justify-center">
        <h1 className=" text-neutral-100 text-4xl py-4 px-4 bg-gradient-to-r from-sky-500 via-purple-400 to-pink-400 inline-block text-transparent bg-clip-text"> Jellyfin Manager </h1>
      </div>
      
      <div className=" flex justify-between mx-48 px-4">
        <div className=" items-center flex gap-5 py-5">
         <button className=" rounded-lg bg-indigo-500 hover:shadow-lg hover:bg-indigo-800 px-4" onClick={() => setNavbar(!navbar)}>
           <p className=" text-neutral-100 text-2xl py-2 hover:text-indigo-400 "> Library </p>
          </button>

         <button className=" rounded-lg bg-indigo-500 hover:shadow-lg hover:bg-indigo-800 px-4" onClick={() => setNavbar(!navbar)}>
           <p className=" text-neutral-100 text-2xl py-2 hover:text-indigo-400 "> Queue </p>
         </button>
        </div>  
      
        <div className=" flex gap-5 py-5">
         <button className="flex text-neutral-100 hover:text-indigo-400" 
             onClick={() => playQueue("b368b8caa5d416f928160d3002831dbe")}>
            <p className="text-3xl py-3 botom-2"> Play </p>
            <div className="flex flex-col align-middle justify-center">
            <MdOutlineCast className="text-4xl"/>
            </div>
         </button>
        </div>
      </div> 
      <hr></hr>
    </header>

      
      <Queue />
      <Library/>
      </main>
      
    </>
  );
}
""
