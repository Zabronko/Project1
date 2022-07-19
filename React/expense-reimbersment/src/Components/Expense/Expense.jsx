export const Expense = ({expense, expenses}) => {

    return (
        <>
            <tr>
                <td>{expense.name}</td>
                <td>{expense.description}</td>
                <td>{expense.cost}</td>
                <td>{expense.status}</td>
            </tr>
        </>
    );
}