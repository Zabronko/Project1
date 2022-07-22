import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { Navigation } from './Components/Navigation';
import { Link } from 'react-router-dom'
import { TicketList } from './Components/Ticket/TicketList'
import { Error } from './pages/Error'
import { Home } from './pages/Home';
import {Info} from './pages/Info'
import { TicketOverview } from './Components/Ticket/TicketOverview';
import { NewTicket } from './Components/Ticket/NewTicket';

export const App = () => {
  return (
    <>
      <BrowserRouter>
        <Navigation>
          <section className='nav-section'>
            <div className='nav-item'>
              <Link className="nav-item" to="/">Home</Link>
            </div>
            <div className='nav-item'>
              <Link className="nav-item" to="/Tickets">Tickets</Link>
            </div>
            <div className='nav-item'>
              <Link className="nav-item" style={{ fontSize: '24px' }} to="/newTicket">Create New Ticket</Link>
            </div>
          </section>
          <section className='nav-section'>
            <div className='nav-item'>Sign In</div>
            <div className='nav-item'>Sign Up</div>
          </section>
        </Navigation>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/Tickets" element={<TicketList />} />
          <Route path="/Tickets/*" element={<TicketOverview />} />
          <Route path="/newTicket" element={<NewTicket />} />
          <Route path="/info" element={<Info />} />
          <Route path="*" element={<Error />} />
        </Routes>
      </BrowserRouter>
      </>
  );
}
