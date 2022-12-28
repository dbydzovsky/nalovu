import { Button, CircularProgress, LinearProgress, Stack } from '@mui/material'
import React from 'react'
import { sendAnswer } from '../action/api'
import SockJsComponent, { GameEvent, OnConnectionProps } from '../sock/SockJsComponent'
import { RedirectIfNotLogin } from './RedirectIfNotLoggedIn'
import Cookies from 'js-cookie';
import { Wrapper } from '../components/Wrapper'
import { Loading } from './Loading'
import { Game } from '../components/runtime/Game'
import { useGameRuntime } from '../components/game'

export function GameRuntime () {
    const [connected, setConnected] = React.useState(false)
    const send = () => {
        sendAnswer({})
    }
    const onConnection = (connectionProps: OnConnectionProps ) => {
        setConnected(connectionProps.connected)
    }
    const onEvent = (event: GameEvent)=> {
        console.log(JSON.stringify(event))
    }
    const gameId = Cookies.get('actualgame') as string
    const game = useGameRuntime()
    const isLoading = !connected && !game.loaded 
    return <div>
        <SockJsComponent gameId={gameId} onConnection={onConnection} onEvent={onEvent}/>
        <RedirectIfNotLogin/>
        { isLoading && <Wrapper Decorated={Loading} />}
        { !isLoading && <div>
            <Game/>
            <Button onClick={send}>Send event</Button>    
        </div>}
        
    </div>

}