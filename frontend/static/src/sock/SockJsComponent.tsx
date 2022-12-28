import React, { useState } from 'react';
// @ts-ignore
import SockJsClient from 'react-stomp';
import { updateGameForClients } from '../action/api';
import { GameRuntime, updateGame } from '../features/game';
import store from '../store';

const SOCKET_URL = '/ws-message';

export interface GameEvent {

}

export interface Payload {
  type: "Event" | "Game"
  game?: GameRuntime
  event?: GameEvent
}

export interface SocketMessage {
  payload: Payload,
  headers: {
    id: string
    timestamp: number
  }
}

export interface OnConnectionProps {
  connected: boolean
}
export interface SockJsComponentProps {
  gameId: string
  onConnection: (props:OnConnectionProps) => void
  onEvent: (event: GameEvent) => void
}

export const SockJsComponent = (props: SockJsComponentProps) => {  
  const [count, setCount] = React.useState(0)
  React.useEffect(() => {
    if (count === 1) {
      store.dispatch(updateGameForClients())
    }
  }, [count])
  let onConnected = () => {
    setCount(count + 1)
    props.onConnection({connected: true})
  }
  let onDisconnect = () => {
    props.onConnection({connected: false})
  }

  let onMessageReceived = (msg: SocketMessage) => {
    console.log(JSON.stringify(msg.payload));
    if (msg.payload.type === "Game" ) {
      store.dispatch(updateGame(msg.payload.game as GameRuntime))
    } else if (msg.payload.type === "Event") {
      props.onEvent(msg.payload.event as GameEvent)
    } else {
      alert("Unsupported message: " + JSON.stringify(msg))
    }
  }

  return (
    <div>
      <SockJsClient
        url={SOCKET_URL}
        topics={['/topic/message/'+props.gameId]}
        onConnect={onConnected}
        onDisconnect={onDisconnect}
        onMessage={(msg:SocketMessage) => onMessageReceived(msg)}
        debug={false}
      />
    </div>
  );
}

export default SockJsComponent;