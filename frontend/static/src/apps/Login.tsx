import React from 'react'
import { Paths } from '..'
import { login } from '../action/api'
import { useMyTransition } from '../features/transition'
import store from '../store'
import { OnSubmitProps, UserPassForm } from './UserPassForm'

export function Login () {
    const transition = useMyTransition()
    const onSubmit = (props: OnSubmitProps) => {
        store.dispatch(login({
            creds: props,
            onOk: () => {
                transition.openPage({url: Paths.root})
            }
        }))
    }
    return <UserPassForm text="Přihlásit" onSubmit={onSubmit}></UserPassForm>
}