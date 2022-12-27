import React from 'react';
import './App.css';
import SockJsComponent from './sock/SockJsComponent';
import { sendAnswer } from './action/api';
import { useDispatch } from 'react-redux';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import { useTransition } from './features/transition';
import { Paths } from '.';
import { createStyles, makeStyles } from '@mui/styles';
import { ActualUser } from './components/ActualUser';
import { useUser } from './components/user';


function App() {
  const answer = (e: any) => {
    sendAnswer({gameId: 6})

  }
  const user = useUser()
  const loggedIn = user !== undefined
  const transition = useTransition()
  const classes = useStyles()
  return (
    <div className={classes.root}>
        <Stack spacing={2} direction="column">
        <ActualUser/>
        <Button disabled={!loggedIn} variant="outlined" onClick={transition.createS(Paths.newGame)}>Nová hra</Button>
        <Button disabled={!loggedIn} variant="outlined" onClick={transition.createS(Paths.joinGame)}>Připojit se ke hře</Button>
        {!loggedIn && <Button variant="outlined" onClick={transition.createS(Paths.register)}>Registrovat</Button>}
        {!loggedIn && <Button variant="outlined" onClick={transition.createS(Paths.login)}>Přihlásit</Button>}
        {loggedIn && <Button variant="outlined" onClick={transition.createS(Paths.logout)}>Odhlásit</Button>}
      </Stack>
    </div>
  );
}

const useStyles = makeStyles(() => {
  return {
    root: {
      margin: 10,
      padding: 10,

    }
  }
})
export default App;
