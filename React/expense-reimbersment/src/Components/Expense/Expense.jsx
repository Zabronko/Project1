import axios from "axios";
import { useRef } from "react";
import save from "../../images/approve.png"
import del from "../../images/trashcan.png"

export const Expense = ({setTicket, mode, expense}) => {

    const handleDelete = async () => {
        try {
            const {data} = await axios.delete(`http://localhost:8080/Project1/expense?expenseId=${expense.expenseId}`)
            setTicket(data);
        } catch (err) {
            console.log(err);
        }
    }

    const handleSave = async () => {
        try {
            const {data} = await axios.put(`http://localhost:8080/Project1/expense?expenseId=${expense.expenseId}&name=${nameRef.current.value!==""? nameRef.current.value : nameRef.current.placeholder}&desc=${descRef.current.value!==""? descRef.current.value : descRef.current.placeholder}&cost=${costRef.current.value!==""? costRef.current.value : costRef.current.placeholder}`)
            nameRef.current.value=null;
            descRef.current.value=null;
            costRef.current.value=null;
            setTicket(data);
        } catch (err) {
            console.log(err);
        }
    }

    const nameRef = useRef();
    const descRef = useRef();
    const costRef = useRef();


    if(mode==='read') {
        return (
            <>
                <tr>
                    <td>{expense.expenseId}</td>
                    <td>{expense.name}</td>
                    <td>{expense.description}</td>
                    <td>${expense.cost}</td>
                </tr>
            </>
        );
    }else {
        return (
            <>
                <tr>
                    <td>{expense.expenseId}</td>
                    <td><input ref={nameRef} className="inheritTable" placeholder={expense.name}/></td>
                    <td><input ref={descRef} className="inheritTable" placeholder={expense.description}/></td>
                    <td><input ref={costRef} className="inheritTable" placeholder={expense.cost}/></td>
                    <td style={{border:"none", padding:"5px", display:"flex"}}>
                        <button onClick={handleSave}><img src={save} alt='Save'/></button>
                        <button onClick={handleDelete}><img src={del} alt='Delete'/></button>
                    </td>
                </tr>
            </>
        );
    }
}