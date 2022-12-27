import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { getUser } from '../action/api'
import { setUser, userSlice } from '../features/user'
import store from '../store'


export function UserLoader () {
    const user = useSelector((state: any) => state.user.value)
    
    React.useEffect(() => {
        store.dispatch(getUser())
    }, [])
    return null
}