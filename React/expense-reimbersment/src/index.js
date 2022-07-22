import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { App } from './App';
import reportWebVitals from './reportWebVitals';
import background from './images/backgroundimg.jpg'

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <section style={{ backgroundImage: `url(${background})`, backgroundAttachment:'fixed', backgroundSize:"100%,100%", minHeight:'100vh'}}>
    <App />
  </section>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
