import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import store from './store'
import { Provider } from 'react-redux'
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import { Register } from './apps/Register';
import { Login } from './apps/Login';
import '@fontsource/roboto/300.css';
import { createStyles, makeStyles } from '@mui/styles';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import SockJsComponent from './sock/SockJsComponent';
import axios from 'axios';
import { JoinGame } from './apps/JoinGame';
import { NewGame } from './apps/NewGame';
import { Wrapper } from './components/Wrapper';

axios.defaults.withCredentials = true

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

export const Paths = {
  register: "/register",
  login: "/login",
  joinGame: "/join",
  newGame: "/newGame"
}

const router = createBrowserRouter([
  {
    path: Paths.register,
    element: <Wrapper Decorated={Register} />,
  },{
    path: Paths.login,
    element: <Wrapper Decorated={Login} />,
  },{
    path: Paths.joinGame,
    element: <Wrapper Decorated={JoinGame} />,
  },{
    path: Paths.newGame,
    element: <Wrapper Decorated={NewGame} />,
  }, {
    path: "/*",
    element: <Wrapper Decorated={App}/>,
  },
]);

const theme = createTheme();
root.render(
  <React.StrictMode>
      <Provider store={store}>
        <SockJsComponent/>
        <ThemeProvider theme={theme}>
          <RouterProvider router={router} />
        </ThemeProvider>
      </Provider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
