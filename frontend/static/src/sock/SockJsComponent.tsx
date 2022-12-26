import React, { useState } from 'react';
// @ts-ignore
import SockJsClient from 'react-stomp';
import { getBaseUrl } from '../utils/Web';

const SOCKET_URL = getBaseUrl() + '/ws-message';

export const SockJsComponent = () => {
  const [message, setMessage] = useState('You server message here.');

  let onConnected = () => {
    console.log("Connected!!")
  }

  let onMessageReceived = (msg: object) => {
    setMessage(JSON.stringify(msg));
  }

  return (
    <div>
      <SockJsClient
        url={SOCKET_URL}
        topics={['/topic/message']}
        onConnect={onConnected}
        onDisconnect={console.log("Disconnected!")}
        onMessage={(msg:object) => onMessageReceived(msg)}
        debug={false}
      />
    </div>
  );
}

export default SockJsComponent;