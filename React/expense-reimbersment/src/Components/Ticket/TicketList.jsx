import { useState, useEffect, useRef } from 'react';
import axios from 'axios';
import { Ticket } from './Ticket';

export const TicketList = () => {
    const [tickets, setTickets] = useState([]);
    const currSortRef = useRef();
    const orderRef = useRef();

    const result = Array.isArray(tickets) ? tickets.map(element => element + 1) : [];
    console.log(result);

    useEffect(() => {
        axios.get('http://localhost:8080/Project1/home')
            .then(res => setTickets(res.data));
        //sets my devs state to be that array of devs
    }, []); //be sure this is an empty array

    const sort = async (sort) => {
        if (currSortRef.current === sort) {
            if (orderRef.current === "asc") {
                orderRef.current = "desc";
            } else {
                orderRef.current = "asc";
            }
            axios.get(`http://localhost:8080/Project1/sort?sort=${sort}&order=${orderRef.current}`)
                .then(res => setTickets(res.data));
            currSortRef.current = sort;
        } else {
            orderRef.current = "asc"
            axios.get(`http://localhost:8080/Project1/sort?sort=${sort}&order=${orderRef.current}`)
                .then(res => setTickets(res.data));
            currSortRef.current = sort;
        }
    }

    const getArrow = (sort) => {
        if(currSortRef.current === sort){
            if (orderRef.current === 'asc') {
                return '↑';
            } else {
                return '↓';
            }
        }else {
            return '-';
        }
    }

    return (
        <section className='SectionContainer'>
            <section className='TicketBox'>
                <h1 style={{ fontSize: "24px" }}>Tickets</h1>
                <table className='TicketTable'>
                    <thead>
                        <tr>
                            <th onClick={() => sort('id')}>Ticket Id{getArrow('id')}</th>
                            <th onClick={() => sort('name')}>Name{getArrow('name')}</th>
                            <th onClick={() => sort('department')}>Department{getArrow('department')}</th>
                            <th onClick={() => sort('totalCost')}>Cost{getArrow('totalCost')}</th>
                            <th onClick={() => sort('status')}>Status{getArrow('status')}</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {tickets.map((ticket) => {
                            return (
                                <Ticket key={ticket.id} tickets={tickets} setTickets={setTickets} ticket={ticket} />
                            );
                        })}

                    </tbody>
                </table>
            </section>
        </section>
    );
}