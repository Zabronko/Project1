import { Expense } from "../Expense/Expense";

export const Ticket = ({ ticket, expenses }) => {

    return (
        <>
            <tr>
                {expenses.map((expense) => {
                    // Map constructs a new array based off what we return
                    return (
                        // Always use the primary id from the database since it's unique
                        <Expense key={expense.expense_id} expenses={expenses} />
                    );
                })}
            </tr>
        </>
    );
}