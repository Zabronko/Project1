import { useContext } from "react";
import ThemeContext from "../contexts/ThemeContext";

export const Navigation = ({children}) => {
    const theme = useContext(ThemeContext);

    return (
        <nav className="navbar" style={theme}>
            {children}
        </nav>
    );
}