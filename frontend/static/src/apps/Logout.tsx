import React, { useTransition } from 'react'
import { Paths } from '..'
import { logout, LogoutProps } from '../action/api'
import { useMyTransition } from '../features/transition'
import store from '../store'
import { RedirectIfNotLogin } from './RedirectIfNotLoggedIn'

export function Logout () {
    const transition = useMyTransition()
    React.useEffect(() => {
        const props: LogoutProps = {
            onDone: () => {
                transition.openPage({url: Paths.root})
            }
        }
        store.dispatch(logout(props))    
    }, []) 
    return <div>
        <RedirectIfNotLogin/>
        Logout
    </div>
}