/* eslint-disable @typescript-eslint/no-unsafe-return */
/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable @next/next/no-img-element */
/* eslint-disable @typescript-eslint/no-unsafe-member-access */
/* eslint-disable @typescript-eslint/no-unsafe-call */
/* eslint-disable @typescript-eslint/no-floating-promises */
/* eslint-disable @typescript-eslint/no-unsafe-argument */
/* eslint-disable @typescript-eslint/no-unsafe-assignment */
import Head from "next/head";
import Link from "next/link";
import Image from "next/image";
import { useDrag } from 'react-dnd'
import { useEffect, useState } from "react";
import { FaPlus } from "react-icons/fa";



  export default function Library() {
    const [movieList, setMovieList] = useState<any[]>([]);
    const [selectedMovies, setSelectedMovies] = useState<string[]>([]);

    useEffect(() => {
        const callAPI = async () => {
          try {
            const res = await fetch("http://172.20.75.133:8080/database/getMovieList");
            const data = await res.json();
            setMovieList(data);
          } catch (err) {
            console.log(err);
          }
        };
    
        callAPI();
      }, []);
    
      const handleMovieClick = async (movieId: string) => {
        try {
    const apiUrl = "http://172.20.75.133:8080/queue/queueAll";
    const requestBody = movieId;
    console.log(movieId);

    const res = await fetch(apiUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: requestBody,
    });
    
          if (res.ok) {
            console.log(`Movie with ID ${movieId} was successfully sent to the API.`);
          } else {
            console.log(`Failed to send movie with ID ${movieId} to the API.`);
          }
        } catch (err) {
          console.log(err);
        }
    
        // Add the selected movie ID to the state
        setSelectedMovies(prevState => [...prevState, movieId]);
      };

      const handleButtonClick = () => {
        console.log('Button clicked!');
    };
    
    return (
    <section id="library" className="py-1.5 mx-48 ">
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-8 mx-4">
        {movieList.map((item,idx) => {
            return (
                <div
                key={idx}
                className="flex flex-col border-indigo-200 rounded-xl shadow-md p-4 bg-indigo-200 hover:border-indigo-400 hover:bg-indigo-400 hover:text-white transition duration-300 ease-in-out focus:outline-none"
                >
      
                  <div className="flex flex-col h-full mx-auto gap-2">
                    <img
                        src={'http://100.122.109.103:8096/Items/'+ item.id 
                            +'/Images/Primary?enableImageTypes=Primary&api_key=f30857bd110848ef9086e3e2b872adbe'}
                        alt=""
                        width={200}
                        height={300}
                        className="rounded-xl shadow-xl content-center"
                    />
                      <button
                  className="bg-indigo-900 text-white p-2 rounded-full mt-2 hover:bg-indigo-200 hover:text-indigo-900"
                  onClick={() => handleMovieClick(item.id)}
                >
                  Add to Queue
                </button>
                       <div className=" text-xl mx-auto"><p>{item.movieTitle} <span 
                           className="text-l">({item.productionYear})</span></p></div>
                </div>
              </div>
            )

         })}
        </div>
    </section>

   );
}