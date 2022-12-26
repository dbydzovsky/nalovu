import React, { ReactNode } from 'react'

import {makeStyles} from '@mui/styles'
import { createTheme, ThemeProvider } from '@mui/material/styles';

export interface WrapperProps {
    Decorated: any
}

export function Wrapper(props: WrapperProps) {
    const Decorated = props.Decorated
    const classes = useStyles()
    return <div className="App">
        <header className="App-header">
            <Decorated/>
        </header>
    </div>
}

const useStyles = makeStyles((theme) => {
    return {
        root: {

        }
    }
})