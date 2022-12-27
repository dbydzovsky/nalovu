
import React from 'react'
import { Paths } from '..'
import { useUser } from '../components/user'
import { useMyTransition } from '../features/transition'

export function RedirectIfNotLogin() {
    const user = useUser()
    const transition = useMyTransition()
    React.useEffect(() => {
        if (user.loaded && !user.logged) {
            transition.openPage({url: Paths.root})
        }
    }, [user])
    return null
}