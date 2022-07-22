import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { App } from './App';
import reportWebVitals from './reportWebVitals';
import background from './images/backgroundimg.jpg'

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <>
    <div style={{ backgroundImage: `url(${background})`, backgroundAttachment: 'fixed', backgroundSize: "100%,100%", height:"90vh" }}>
      <App />
    </div>
    <footer style={{height:"10vh", justifyContent:"center"}}>
      <h2 style={{textAlign:"center", margin:"0px"}}>MegaTech</h2>
      <section style={{display:'grid', gridAutoFlow: 'column', margin:"0px", justifyContent:"center"}}>
        <p style={{marginLeft:"0px",fontSize:"20px"}} onClick={event =>  window.location.href='/error'} className='noDec'>Error Page</p>
        <p style={{fontSize:"20px"}} onClick={event =>  window.location.href='/info'} className='noDec'>Info</p>
      </section>
      <p style={{textAlign:"center", margin:"0px"}}>© 2022 MegaTech. All Rights Reserved.</p>
    </footer>
  </>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
