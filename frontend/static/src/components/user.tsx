import React from 'react'
import { useSelector } from 'react-redux';
import { User } from '../data/User';
import { MyUser } from '../features/user';


export function useUser() :MyUser {
    const user: MyUser = useSelector((state: any) => state.user.value)
    return user
}