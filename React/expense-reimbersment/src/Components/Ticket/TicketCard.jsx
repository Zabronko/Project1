import React, { useRef } from 'react'
import { Expense } from '../Expense/Expense';


export const TicketCard = ({ticket}) => {
  console.log(ticket.status.status)
  return (
    <>
      <h1>{ticket.id}</h1>
      <h2>{ticket.name}</h2>
      <h2>{ticket.status.status}</h2>
    </>
  );
}