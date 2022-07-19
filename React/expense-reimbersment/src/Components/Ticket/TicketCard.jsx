import React from 'react'
import { Expense } from '../Expense/Expense';

export const TicketCard = ({
  ticket, expenses
}) => (
  <table>
    <thead>
      <tr>
        <td>Ticket Id</td>
        <td>Expenses</td>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>{ticket}.ticket_id</td>
      </tr>
      {expenses.map((expense) => {
        // Map constructs a new array based off what we return
        return (
          // Always use the primary id from the database since it's unique
          <Expense key={expense.expenseId} expense={expense} expenses={expenses} />
        );
      })}
    </tbody>
  </table>
)