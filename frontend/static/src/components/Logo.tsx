import React from 'react'
import { Paths } from '..'
import { useTransition } from '../features/transition'

export function Logo( ) {

    const transition = useTransition()
    return <div onClick={transition.createS(Paths.root)}>
        Na lovu
    </div>
}