import { Button } from '@mui/material';
import { Stack } from '@mui/material';
import React from 'react'
import { getStartMoneyAvailables, startMoneyFight } from '../../action/api';
import { User } from '../../data/User';
import store from '../../store';
import { GenericGameProps } from './Game';
import { Waiting } from './Waiting';

export function SelectMoneyFight(props: GenericGameProps) {
    const [users, setUsers] = React.useState([] as User[])
    React.useEffect(() => {
        store.dispatch(getStartMoneyAvailables({
            onOk: setUsers
        }))
    }, [])
    if (users.length === 0) {
        return <Waiting {...props}/>
    }
    const selectUser = (userId: number) => {
        store.dispatch(startMoneyFight({userId: userId}))
    }
    return <Stack spacing={2} direction="column">
        { users.map(user => {
            return <Button key={user.id} onClick={() => selectUser(user.id)}>
                {user.name}
            </Button>
         })}
    </Stack>
}