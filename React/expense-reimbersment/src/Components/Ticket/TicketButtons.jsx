import axios from "axios";
import { useState } from "react";

export const TicketButtons = ({ tickets, setTickets, ticket }) => {

    const [currticket, setTicket] = useState(ticket);


    const HandleApprove = async (event) => {
        try {
            const {data} = await axios.put(`http://localhost:8080/Project1/Tickets?id=${ticket.id}&status=approved`);
            setTicket(data);
        }
        catch (err) {
            console.error(err);
        }
    }

    const HandleDeny = async (event) => {
        try {
            const {data} = await axios.put(`http://localhost:8080/Project1/Tickets?id=${ticket.id}&status=denied`)
            setTicket(data);
        }
        catch (err) {
            console.error(err);
        }
    }

    const HandleDelete = async (event) => {
        try {
            axios.delete(`http://localhost:8080/Project1/Tickets?id=${ticket.id}`)
            setTickets(tickets.filter(ticket1 => ticket.id !== ticket1.id));
        }
        catch (err) {
            console.error(err);
        }
    }


    if (currticket.status.status === 'pending') {
        return (
            <>
                <button onClick={HandleApprove}>approve</button>
                <button onClick={HandleDeny}>deny</button>
            </>
        );
    } else {
        return (
            <>
                <button onClick={HandleDelete}>delete</button>
            </>
        );
    }

}