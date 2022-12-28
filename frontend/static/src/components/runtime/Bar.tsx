import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import { GenericGameProps } from "./Game";


export function GameBar(props: GenericGameProps) {
    const game = props.game
    const user = props.user
  return (
    <Box sx={{ flexGrow: 1 }}>
        <AppBar position="static">
            <Toolbar>
            <IconButton
                size="large"
                edge="start"
                color="inherit"
                aria-label="menu"
                sx={{ mr: 2 }}
            >
                Na lovu
            </IconButton>
            <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                {game.name}
            </Typography>
            <Button color="inherit">{user.role}: {user.name}</Button>
            </Toolbar>
        </AppBar>
        </Box>
    );
}
