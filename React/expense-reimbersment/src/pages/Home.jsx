import background from './../images/backgroundimg.jpg'
export const Home = () => {
    console.log(background);
    return (
        <section className='SectionContainer'>
        <section style={{height: '100%', justifyContent:'center'}}>
            <h1 style={{textAlign:'center', fontSize:"42px", margin:"0px"}}>Welcome MegaTech Employees!</h1>
            <h2 style={{textAlign:'center', fontSize:"24px"}}>Click <a style={{textDecoration:"none", color:"red"}} href="./newTicket">Here</a> to add a new Ticket</h2>
            <h2 style={{textAlign:'center', fontSize:"24px"}}><a style={{textDecoration:"none", color:"green"}} href="./Tickets">View Tickets</a></h2>
        </section>
        </section>
    );
}