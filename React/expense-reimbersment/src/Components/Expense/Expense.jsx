export const Expense = ({expense, expenses}) => {

    return (
        <>
            <tr>
                <td>{expense.expenseId}</td>
                <td>{expense.name}</td>
                <td>{expense.description}</td>
                <td>{expense.cost}</td>
            </tr>
        </>
    );
}