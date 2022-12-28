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
import { Logout } from './apps/Logout';
import { UserLoader } from './components/UserLoader';
import { GameRuntime } from './apps/GameRuntime';

axios.defaults.withCredentials = true

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

export const Paths = {
  root: "/",
  register: "/register",
  login: "/login",
  logout: "/logout",
  joinGame: "/join",
  newGame: "/newGame",
  gameRuntime: "/game",
}

// https://mui.com/material-ui/react-button/
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
    path: Paths.gameRuntime,
    element: <GameRuntime/>,
  },{
    path: Paths.newGame,
    element: <Wrapper Decorated={NewGame} />,
  }, {
    path: Paths.logout,
    element: <Wrapper Decorated={Logout} />,
  }, {
    path: "/*",
    element: <Wrapper Decorated={App}/>,
  },
]);

const theme = createTheme();
root.render(
  <React.StrictMode>
      <Provider store={store}>
        <UserLoader/>
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
