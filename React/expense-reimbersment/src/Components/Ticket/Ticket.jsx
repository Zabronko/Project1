import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import approve from '../../images/approve.png'
import deny from '../../images/deny.jpg'
import del from '../../images/trashcan.png'

export const Ticket = ({ tickets, setTickets, ticket, expenses }) => {

    const status = ticket.status.statusId === 1 ? 'yellow' : ticket.status.statusId === 2 ? 'green' : 'red';
    const history = useNavigate()

    const handleClick = () => {
        history(`./${ticket.id}`)
    }

    const HandleApprove = async (event) => {
        try {
            const { data } = await axios.put(`http://localhost:8080/Project1/Tickets?id=${ticket.id}&status=approved`);
            setTickets(data);
        }
        catch (err) {
            console.error(err);
        }
    }

    const HandleDeny = async (event) => {
        try {
            const { data } = await axios.put(`http://localhost:8080/Project1/Tickets?id=${ticket.id}&status=denied`)
            setTickets(data);
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

    if (ticket.status.statusId === 1) {
        return (
            <>
                <tr style={{ backgroundColor: status }}>
                    <td onClick={handleClick}>{ticket.id}</td>
                    <td onClick={handleClick}>{ticket.name}</td>
                    <td onClick={handleClick}>{ticket.department}</td>
                    <td onClick={handleClick}>${ticket.totalCost}</td>
                    <td onClick={handleClick}>{ticket.status.status}</td>
                    <td>
                        <>
                            <button onClick={HandleApprove}><img src={approve} alt='Approve'/></button>
                            <button onClick={HandleDeny}><img src={deny} alt='Deny'/></button>
                        </>
                    </td>
                </tr>
            </>
        );
    } else {
        return (
            <>
                <tr style={{ backgroundColor: status }}>
                    <td onClick={handleClick}>{ticket.id}</td>
                    <td onClick={handleClick}>{ticket.name}</td>
                    <td onClick={handleClick}>{ticket.department}</td>
                    <td onClick={handleClick}>${ticket.totalCost}</td>
                    <td onClick={handleClick}>{ticket.status.status}</td>
                    <td>
                    <button onClick={HandleDelete}><img src={del} alt='Delete'/></button>
                    </td>
                </tr>
            </>
        );
    }
}