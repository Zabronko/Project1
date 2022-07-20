import { useState } from "react";

export const Ticket = ({ ticket, expenses }) => {

    const [status, setStatus] = useState(ticket.status.statusId === 1 ? 'yellow' : ticket.status.statusId === 2 ? 'green':'red');
    

    return (
        <>
            <tr style={{backgroundColor: status}}>
                <td>{ticket.id}</td>
                <td>{ticket.name}</td>
                <td>{ticket.department}</td>
                <td>{ticket.totalCost}</td>
                <td>{ticket.status.status}</td>
            </tr>
        </>
    );
}