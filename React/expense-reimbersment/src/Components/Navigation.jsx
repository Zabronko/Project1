import { useContext } from "react";

export const Navigation = ({children}) => {

    return (
        <nav className="navbar">
            {children}
        </nav>
    );
}