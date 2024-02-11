/* eslint-disable @typescript-eslint/no-unsafe-call */
/* eslint-disable @typescript-eslint/no-misused-promises */
/* eslint-disable @typescript-eslint/no-floating-promises */
/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable @typescript-eslint/no-unsafe-assignment */
/* eslint-disable @typescript-eslint/no-unsafe-argument */
/* eslint-disable @typescript-eslint/no-unsafe-member-access */
import Head from "next/head";
import Link from "next/link";
import { json } from "node:stream/consumers";
import { useState, useEffect } from 'react';
import { TiArrowSortedUp, TiArrowSortedDown } from "react-icons/ti";

export default function Queue() {
    const [queueList, setQueueList] = useState<any[]>([]);

    useEffect(() => {
        const fetchQueue = async () => {
            try {
                const res = await fetch("http://172.20.75.133:8080/queue/returnQueue");
                const data = await res.json();
                setQueueList(data.mediaArray);
            } catch (err) {
                console.log(err);
            }
        };
        
        fetchQueue();

        // Fetch queue every 10 seconds
        const intervalId = setInterval(fetchQueue, 100);

        // Cleanup function to clear interval
        return () => clearInterval(intervalId);
    }, []);

    const MoveItemUp = async (id: number) => {
        try {
            const apiUrl = "http://172.20.75.133:8080/queue/moveItemUp";
            const requestBody = id;
            console.log(id);
        
            const res = await fetch(apiUrl, {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json',
              },
              body: requestBody,
            });

            if (res.ok) {
                console.log(`yay`);
            } else {
                console.log(`Fwomp womp`);
            }
        } catch (err) {
            console.log(err);
        }
    };
    const MoveItemDown = async (id: number) => {
        try {
            const apiUrl = "http://172.20.75.133:8080/queue/moveItemDown";
            const requestBody = id;
            console.log(id);
        
            const res = await fetch(apiUrl, {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json',
              },
              body: requestBody,
            });

            if (res.ok) {
                console.log(`yay`);
            } else {
                console.log(`Fwomp womp`);
            }
        } catch (err) {
            console.log(err);
        }
    };
    const removeItem = async (id: number) => {
        try {
            const apiUrl = "http://172.20.75.133:8080/queue/removeFromQueue";
            const requestBody = id;
            console.log(id);
        
            const res = await fetch(apiUrl, {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json',
              },
              body: requestBody,
            });

            if (res.ok) {
                console.log(`yay`);
            } else {
                console.log(`Fwomp womp`);
            }
        } catch (err) {
            console.log(err);
        }
    };
    const stopQueue = async (sessionId: string) => {
        try {
            const apiUrl = "http://172.20.75.133:8080/sessions/command";
            const requestBody = JSON.stringify([sessionId, "Stop"]);
    
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
        <section id="queue" className="py-1.5">
            <div className="flex flex-col justify-evenly mx-4 gap-4 py-4">
                {queueList.map((item, idx) => (
                    <div key={idx} className="flex justify-between mx-48 rounded-lg bg-indigo-300 px-4 py-4 text-slate-900">
                        <div className="flex gap-6">
                            <div className="flex flex-col justify-center">
                            <button onClick={() => MoveItemUp(idx)}> 
                                        <TiArrowSortedUp className="text-6xl cursor-pointer text-slate-400 hover:text-slate-600" />
                                </button> 
                                <button onClick={() => MoveItemDown(idx)}> 
                                    <TiArrowSortedDown className="text-6xl cursor-pointer text-slate-400 hover:text-slate-600" />
                                </button>
                            </div>
                            <img
                                src={`http://100.122.109.103:8096/Items/${item.id}/Images/Primary?enableImageTypes=Primary&api_key=f30857bd110848ef9086e3e2b872adbe`}
                                alt=""
                                width={200}
                                height={200}
                                className="rounded-xl shadow-xl content-center"
                            />
                            <div className="flex flex-col">
                                <div className="text-2xl">
                                    <p>{item.name} <span className="text-xl">({item.year})</span></p>
                                </div>
                                {item.isShow && (
                                    <div>
                                        <p>Season: {item.season}</p>
                                        <p>Episode: {item.episode}</p>
                                    </div>
                                )}
                                <div><h3>Duration: {item.length} minutes</h3></div>
                                <div><p>Description: {item.overview}</p></div>
                            </div>
                        </div>
                        <div className="flex flex-col justify-center gap-4 ml-2">
                            <button className="rounded-lg bg-red-600 hover:bg-red-800 px-1 items-end">
                                <p className="text-neutral-100 py-1.5 px-1.5 hover:text-red-100 text-ms"  onClick={() => removeItem(idx)}>Remove</p>
                            </button>
                            {(idx == 0 ? <><button className="rounded-lg bg-red-600 hover:bg-red-800 px-1 items-end">
                                <p className="text-neutral-100 py-1.5 px-1.5 hover:text-red-100 text-ms"  onClick={() => stopQueue("b368b8caa5d416f928160d3002831dbe")}>Stop</p>
                            </button></> : <></>)}
                        </div>
                    </div>
                ))}
            </div>
        </section>
    );
}
