import axios from "axios";
import { useNavigate } from "react-router-dom";

export const NewTicket = () => {

    const history = useNavigate();


    const createNew = async () => {
        try {
            const {data} = await axios.post('http://localhost:8080/Project1/newTicket');
            history(`../tickets/${data.id}`)
        } catch (err) {
            console.log(err);
        }
    }

    createNew();

}