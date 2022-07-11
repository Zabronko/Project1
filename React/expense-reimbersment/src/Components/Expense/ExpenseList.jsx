import { useState, useEffect, useRef, useContext } from 'react';
import ThemeContext from '../../contexts/ThemeContext';
import axios from 'axios';
import { Expense } from './Expense';

export const ExpenseList = () => {
    const [expenses, setExpenses] = useState([]);
    const [name, setName] = useState('');
    //UseRef hook -- returns a reference object that will persist between re-renders similar to useState
    // The key diff though, is that updating a ref, does not trigger a re-render
    /*
        1. refs are mutable and i can udate them without a setter
        2. mutating a ref does not cause a re-render
        3. The value is persisted between re-renders


    */
    const notesRef = useRef();
    //const StatusIdRef = useRef();

    const theme = useContext(ThemeContext);

    useEffect(() => {
        axios.get('http://localhost:8080/Project1/home')
            .then(res => setExpenses(res.data));
         //sets my devs state to be that array of devs
    }, []); //be sure this is an empty array

    console.log(expenses);

    const handleSubmit = async (event) => {
        try {
            event.preventDefault(); //prevent the default HTML form submission (AKA reload the page)
            // 1. extract the data

            // 2. send out a POST request
            // 3. When you receive the newly created dev id, add it to the dev array
            const {data} = await axios.post(`http://localhost:8080/Project1/home?name=${name}&notes=${notesRef.current.value}`);
            setExpenses([...expenses, data]);
            setName('');
            notesRef.current.value = null;
            //StatusIdRef.current.value = null;
        } catch (err) {
            console.log(err);
        }
    }

    return (
        <form onSubmit={handleSubmit}>
            <table style={theme}>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Notes</th>
                        <th>Status Id</th>
                    </tr>
                </thead>
                <tbody>
                    {/* I want to dynamically render all of those devs that I received */}
                    {/* <tr> This is the HTML way. Manual typing
                        <td>Dan Pickles</td>
                        <td>Senior Technical Architect</td>
                        <td>100000</td>
                    </tr> */}
                    {/* Use the built in array method, map() to create an array of JSX elements */}
                    {expenses.map((expense) => {
                        // Map constructs a new array based off what we return
                        return (
                            // Always use the primary id from the database since it's unique
                            <Expense key={expense.expenseId} expense={expense} expenses={expenses} setExpenses={setExpenses} />
                        );
                    })}
                    {/* <form> */}
                    <tr>
                            {/* In React, we should NOT use document.getElementById to access
                                a DOM element.
                                
                                Doing so, bypasses React and all of its optimaztions, instead, we should
                                this React's way
                            */}

                            {/* This is a "controlled component". React is in charge of it */}
                            <td><input name="name" value={name} onChange={(event) => setName(event.target.value)} placeholder='Please enter Name'/></td>
                            
                            {/* This is an "uncontrolled component". React doesn't manage it, it just uses
                                the native DOM to manage state */}
                                {/* When input updates, my ref will also update since it "points" to that input */}
                            <td><input name="Notes" ref={notesRef} placeholder='Please enter Notes'/></td>
                    </tr> 
                            <button>Create new Expense!</button>
                
                    {/* </form> */}
                </tbody>
            </table>
        </form>
    );
}