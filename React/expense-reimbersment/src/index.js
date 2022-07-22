import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { App } from './App';
import reportWebVitals from './reportWebVitals';
import background from './images/backgroundimg.jpg'

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <>
    <section style={{ backgroundImage: `url(${background})`, backgroundAttachment: 'fixed', backgroundSize: "100%,100%", minHeight: "85vh" }}>
      <App />
    </section>
    <section style={{ backgroundColor: "white", justifyContent: "center", position: 'absoulute', left: 0, right: 0,bottom:0, marginLeft: "10%", width: "80%" }}>
      <h2 style={{ textAlign: "center", margin: "0px" }}>MegaTech</h2>
      <section style={{ display: 'grid', gridAutoFlow: 'column', margin: "0px", justifyContent: "center" }}>
        <p style={{ marginLeft: "0px", fontSize: "20px" }} onClick={event => window.location.href = '/error'} className='noDec'>Error Page</p>
        <p style={{ fontSize: "20px" }} onClick={event => window.location.href = '/info'} className='noDec'>Info</p>
      </section>
      <p style={{ textAlign: "center", margin: "0px" }}>© 2022 MegaTech. All Rights Reserved.</p>
    </section>
  </>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
