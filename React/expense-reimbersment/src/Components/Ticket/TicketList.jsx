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
            <table>
                <thead>
                    <tr>
                        <td>Ticket Id</td>
                        <td>Name</td>
                        <td>Department</td>
                        <td>Cost</td>
                        <td>Status</td>
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
        </>
    );
}