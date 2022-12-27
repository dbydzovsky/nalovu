import { Button } from '@mui/material'
import React from 'react'
import { sendAnswer } from '../action/api'
import SockJsComponent from '../sock/SockJsComponent'
import { RedirectIfNotLogin } from './RedirectIfNotLoggedIn'
import Cookies from 'js-cookie';
export function Lobby () {
    const send = () => {
        sendAnswer({})
    }
    return <div>
        {Cookies.get('gameid')}
        <SockJsComponent/>
        <RedirectIfNotLogin/>
        <Button onClick={send}>Send event</Button>
    </div>

}