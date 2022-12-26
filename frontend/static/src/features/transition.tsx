import React from 'react'
import { useNavigate } from "react-router-dom";

export interface OpenPageProps {
    url: string
}

export function useTransition() {
    const navigate = useNavigate()
    const transition = {
        openPage: (props:OpenPageProps)=> {
            navigate(props.url)
        },
        create: (props:OpenPageProps) => {
            return (e: any) => {
                e.preventDefault()
                transition.openPage(props)
            }
        },
        createS: (url: string) => {
            return (e: any) => {
                e.preventDefault()
                transition.openPage({url})
            }
        }        
    }   
    return transition
}
