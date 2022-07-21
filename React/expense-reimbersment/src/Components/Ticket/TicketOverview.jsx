import { useState, useEffect } from "react";
import axios from 'axios';
import { Expense } from './../Expense/Expense'

export const TicketOverview = () => {

    const [ticket, setTicket] = useState([]);
    const [expenses, setExpenses] = useState([]);

    useEffect(() => {
        console.log("here")
        axios.get(`http://localhost:8080/Project1/Ticket`, { params: { id: window.location.href.substring(30) } })
            .then(res => {
                setTicket(res.data);
            })
    }, []);

    console.log(ticket);


    if(ticket.expenses !== undefined) {
        return (
            <section className="TicketBox">
                <h1 className="OverviewTitle">Ticket #{ticket.id}</h1>
                <section className="Ticket50Section">
                    <h3 className="OverviewItem">Employee Name: {ticket.name}</h3>
                </section>
                <section className="Ticket50Section">
                    <h3 className="OverviewItem">Department: {ticket.department}</h3>
                </section>
                <section className="Ticket100Section">
                    <h3 className="OverviewItem">Status: {ticket.status.status}</h3>
                </section>
                <section className="Ticket100Section">
                    <table className='TicketTable'>
                        <thead>
                            <tr>
                                <th>Expense Id</th>
                                <th>Name</th>
                                <th>description</th>
                                <th>Cost</th>
                            </tr>
                        </thead>
                        <tbody>
                            {ticket.expenses.map((expense) => {
                                return (
                                    <Expense key={expense.expenseId} expense={expense} expenses={expenses} />
                                );
                            })}
                            <tr>
                                <td></td>
                                <td></td>
                                <td>Total Cost:</td>
                                <td>{ticket.totalCost}</td>
                            </tr>
                        </tbody>
                    </table>
                </section>
            </section>
        );
                        }
}