import { useState, useEffect } from 'react';
import axios from 'axios';
import { Ticket } from './Ticket';

export const TicketList = () => {
    const [tickets, setTickets] = useState([]);

    const result = Array.isArray(tickets) ? tickets.map(element => element + 1) : [];
    console.log(result);

    useEffect(() => {
        axios.get('http://localhost:8080/Project1/home')
            .then(res => setTickets(res.data));
        //sets my devs state to be that array of devs
    }, []); //be sure this is an empty array

    console.log(tickets);

    return (
        <>
            <section className='TicketBox'>
                <table className='TicketTable'>
                    <thead>
                        <tr>
                            <th>Ticket Id</th>
                            <th>Name</th>
                            <th>Department</th>
                            <th>Cost</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        {tickets.map((ticket) => {
                            return (
                                <Ticket key={ticket.id} ticket={ticket} />
                            );
                        })}
                    </tbody>
                </table>
            </section>
        </>
    );
}