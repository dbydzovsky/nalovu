import React, { useState } from 'react';
// @ts-ignore
import SockJsClient from 'react-stomp';

const SOCKET_URL = '/ws-message';

export const SockJsComponent = () => {
  const [message, setMessage] = useState('You server message here.');

  let onConnected = () => {
    console.log("Connected!!")
  }

  let onMessageReceived = (msg: object) => {
    alert(JSON.stringify(msg))
    setMessage(JSON.stringify(msg));
  }

  return (
    <div>
      <SockJsClient
        url={SOCKET_URL}
        topics={['/topic/message/1']}
        onConnect={onConnected}
        onDisconnect={console.log("Disconnected!")}
        onMessage={(msg:object) => onMessageReceived(msg)}
        debug={false}
      />
      {message}
    </div>
  );
}

export default SockJsComponent;