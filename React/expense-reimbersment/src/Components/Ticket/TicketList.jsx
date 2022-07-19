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
            {tickets.map((ticket) => {
                return (
                    <>
                        <h1>{ticket.id}</h1>
                        {ticket.expenses.map((expense) => {
                            return (
                                <h2>{expense.expenseId}</h2>
                            );
                        })}
                    </>
                );
            })}
        </>
    );
}