import { LinearProgress } from '@mui/material'
import { Stack } from '@mui/system'
import React from 'react'

export function Loading () {
    return <Stack spacing={2} alignContent="center" direction="column">
        Načítám..
        <LinearProgress/>
    </Stack>
}