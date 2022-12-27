import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { getUser } from '../action/api'
import { User } from '../data/User'
import { setUser, userSlice } from '../features/user'
import store from '../store'
import { useUser } from './user'


export function ActualUser () {
    const user =useUser()
    if (user.logged) {
        return <div>{user.name}</div>
    }
    return null
}