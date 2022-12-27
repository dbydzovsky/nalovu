import React from 'react'
import { useSelector } from 'react-redux';
import { User } from '../data/User';


export function useUser() :User|undefined {
    const user:(User|undefined) = useSelector((state: any) => state.user.value)
    return user
}