import React from 'react'
import { Paths } from '..'
import { register } from '../action/api'
import { useMyTransition } from '../features/transition'
import store from '../store'
import { OnSubmitProps, UserPassForm } from './UserPassForm'

export function Register () {
    const transition = useMyTransition()
    const onSubmit = (props: OnSubmitProps) => {
        store.dispatch(register({
            creds: props,
            onOk: () => {
                transition.openPage({url: Paths.login})
            }
        }))
    }
    return <UserPassForm text="Zaregistrovat" onSubmit={onSubmit} />
}