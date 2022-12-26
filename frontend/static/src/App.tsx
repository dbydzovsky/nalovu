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


function App() {

  const answer = (e: any) => {
    sendAnswer({gameId: 6})

  }
  const transition = useTransition()
  return (
    <Stack spacing={2} direction="column">
      <Button variant="outlined" onClick={transition.createS(Paths.newGame)}>Nová hra</Button>
      <Button variant="outlined" onClick={transition.createS(Paths.joinGame)}>Připojit se ke hře</Button>
    </Stack>
  );
}

const useStyles = makeStyles(() => {
  return { 
    root: {

    }
  }
})

export default App;
