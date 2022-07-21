import { useNavigate } from 'react-router-dom';


export const Ticket = ({ ticket, expenses }) => {

    const status = ticket.status.statusId === 1 ? 'yellow' : ticket.status.statusId === 2 ? 'green' : 'red';
    const history = useNavigate();

    const handleClick = () => {
        history(`./${ticket.id}`)
    }

    return (
        <>
            <tr style={{ backgroundColor: status }} onClick={handleClick} >
                <td>{ticket.id}</td>
                <td>{ticket.name}</td>
                <td>{ticket.department}</td>
                <td>{ticket.totalCost}</td>
                <td>{ticket.status.status}</td>
            </tr>
        </>
    );
}