import { createContext } from "react";

export const themes = {
    light: {
        backgroundColor: '#EEEEEE',
        color: 'black'
    },
    dark: {
        backgroundColor: '#191515',
        color: 'white'
    }
}

// If not specified, the default value will be light
const ThemeContext = createContext(themes.light);

export default ThemeContext;