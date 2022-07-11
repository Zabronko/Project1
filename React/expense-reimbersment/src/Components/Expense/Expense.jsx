import axios from "axios";
// props is an object containing all of the properties passed down by the parent
// Because it's an object, I can destructure out what I want
export const Expense = ({expense, setExpenses, expenses}) => {

    const handleDelete = async (e) => {
        try {
            e.preventDefault();
            await axios.delete(`http://localhost:8080/Project1/home?id=${expense.expenseId}`);
            setExpenses(expenses.filter(expense1 => expense.expenseId !== expense1.expenseId));
        }
        catch (err) {
            console.error(err);
        }
    }

    return (
        // Give the text the color of blue
        // inline styles
        <>
            <tr>
                <td>{expense.name}</td>
                <td>{expense.notes}</td>
                <td>{expense.statusId}</td>
            </tr>
            <button onClick={handleDelete}>Delete</button>
        </>
    );
}
// npm i styled-components