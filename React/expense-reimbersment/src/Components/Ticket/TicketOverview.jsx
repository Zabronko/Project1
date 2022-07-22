import { useState, useEffect, useRef } from "react";
import axios from 'axios';
import { Expense } from './../Expense/Expense'
import save from "../../images/save.jpg"

export const TicketOverview = () => {

    const [ticket, setTicket] = useState([]);
    const [expenses, setExpenses] = useState([]);
    const [mode, setMode] = useState('read');

    const nameRef = useRef('');
    const departmentRef = useRef('');
    const notesRef = useRef('');

    const expenseNameRef = useRef('');
    const expenseDescRef = useRef('');
    const expenseCostRef = useRef('');

    useEffect( () => {
        axios.get(`http://localhost:8080/Project1/Tickets?id=${window.location.href.substring(30)}`)
            .then(res => {
                setTicket(res.data);
                nameRef.current = ticket.name;
                departmentRef.current = ticket.department;
                notesRef.current = ticket.notes;
            })
    },[]);

    const changeMode = () => {
        setMode('edit')
    }

    const handleSubmit = async () => {
        try {
            const { data } = await axios.put(`http://localhost:8080/Project1/Ticket?id=${ticket.id}&name=${nameRef.current.value === "" ? nameRef.current.placeholder : nameRef.current.value}&department=${departmentRef.current.value === "" ? departmentRef.current.placeholder : departmentRef.current.value}&notes=${notesRef.current.value === "" ? notesRef.current.placeholder : notesRef.current.value}`)
            setMode('read')
            setTicket(data);
            setExpenses(data.expenses);
        } catch (err) {
            console.log(err);
        }
    }

    const newExpense = async () => {
        console.log(expenseCostRef.current.value);
        try {
            const { data } = await axios.post(`http://localhost:8080/Project1/expense?ticketId=${ticket.id}&name=${expenseNameRef.current.value}&description=${expenseDescRef.current.value}&cost=${expenseCostRef.current.value}`)
            expenseNameRef.current.value = null;
            expenseDescRef.current.value = null;
            expenseCostRef.current.value = null;
            setTicket(data);
            setExpenses(ticket.expenses)
        } catch (err) {
            console.log(err);
        }
    }

    const checkVisibility = (status) => {
        return status==='pending'? 'visible':'hidden'
    }

    if (ticket.expenses !== undefined) {
        if (mode === 'read') {
            return (
                <section className="SectionContainer">
                    <section className="TicketBox">
                        <h1 className="OverviewTitle">Ticket #{ticket.id}</h1>
                        <section className="Ticket50Section">
                            <h3 className="OverviewItem">Employee Name: {ticket.name}</h3>
                        </section>
                        <section className="Ticket50Section">
                            <h3 className="OverviewItem">Department: {ticket.department}</h3>
                        </section>
                        <h2 className="OverviewItem">Notes:</h2>
                        <section className="borderBox">
                            <p style={{ textAlign: 'center' }}>{ticket.notes}</p>
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
                                    {ticket.expenses !== null ? ticket.expenses.map((expense) => {
                                        return (
                                            <Expense key={expense.expenseId} mode={mode} expense={expense} expenses={expenses} />
                                        );
                                    }) : ""}
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td>Total Cost:</td>
                                        <td>${ticket.totalCost}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </section>
                        <button className="editButton" style={{ margin: "10px", visibility: checkVisibility(ticket.status.status)}} onClick={changeMode}>Edit</button>
                    </section>
                </section>
            );
        } else {
            return (
                <section className="SectionContainer">
                    <section className="TicketBox">
                        <h1 className="OverviewTitle">Ticket #{ticket.id}</h1>
                        <section className="Ticket50Section">
                            <h3 className="OverviewItem">Employee Name: <input ref={nameRef} className="inheritAll" placeholder={ticket.name} /></h3>
                        </section>
                        <section className="Ticket50Section">
                            <h3 className="OverviewItem">Department: <input ref={departmentRef} className="inheritAll" placeholder={ticket.department}></input></h3>
                        </section>
                        <h2 className="OverviewItem">Notes:</h2>
                        <section className="borderBox">
                            <textarea placeholder={ticket.notes} ref={notesRef} className="notesInput" />
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
                                    {ticket.expenses !== null ? ticket.expenses.map((expense) => {
                                        return (
                                            <Expense key={expense.expenseId} setTicket={setTicket} mode={mode} expense={expense} />
                                        );
                                    }) : ""}
                                    <tr>
                                        <td></td>
                                        <td><input ref={expenseNameRef} placeholder='Please enter Name' /></td>
                                        <td><input ref={expenseDescRef} placeholder='Please enter Description' /></td>
                                        <td><input ref={expenseCostRef} placeholder='Please enter Cost' /></td>
                                        <td style={{ border: "none" }}><button onClick={newExpense}><img style={{width:"30px", height:"25px"}} src={save} alt='Save'/></button></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td>Total Cost:</td>
                                        <td>{ticket.totalCost}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </section>
                        <button className="editButton" onClick={handleSubmit} style={{ margin: "10px" }}>Submit</button>
                    </section>
                </section >
            );
        }
    }
}