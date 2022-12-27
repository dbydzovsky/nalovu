import React from 'react'
import { GameDto, joinGame, listGames } from '../action/api'
import { UserRole } from '../data/User'
import { useMyTransition } from '../features/transition'
import store from '../store'

import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';

export function JoinGame() {
    const [games, setGames ] = React.useState([] as GameDto[])
    React.useEffect(() => {
        listGames({
            onDone: (games) => {
                setGames(games)
            }
        })
    }, [])

    const transition = useMyTransition()
    const createJoinGame = (id: number, role: UserRole) => (e:any) => {
        e.preventDefault()
        joinGame({
            gameId: id,
            role: role,
            onOk: (game: GameDto) => {
                alert(JSON.stringify(game))
            }
        })
    }
    return <Stack spacing={2} direction="column">
        {games.map(game => {
            return <div>
                    <Stack spacing={2} direction="column">
                    <h2>{game.name}</h2>
                    {!game.hasHunter && <Button onClick={createJoinGame(game.id, UserRole.Hunter)}>
                        Připojit se jako lovec
                    </Button>}
                    <Button onClick={createJoinGame(game.id, UserRole.Player)}>
                        Připojit se jako hráč
                    </Button>
                </Stack>
            </div>
        })}
    </Stack>
}