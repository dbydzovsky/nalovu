import React from 'react'
import { GameDto, joinGame, listGames } from '../action/api'
import { UserRole } from '../data/User'
import { useMyTransition } from '../features/transition'
import store from '../store'

import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import { RedirectIfNotLogin } from './RedirectIfNotLoggedIn'
import { Paths } from '..'
import { useUser } from '../components/user'

export function JoinGame() {
    const [games, setGames ] = React.useState([] as GameDto[])
    const user = useUser()
    React.useEffect(() => {
        listGames({
            onDone: (games) => {
                setGames(games)
            }
        })
    }, [])
    const transition = useMyTransition()
    const openLobby = () => transition.openPage({url: Paths.lobby})
    const createJoinGame = (id: number, role: UserRole, assign: boolean) => (e:any) => {
        e.preventDefault()
        joinGame({
            gameId: id,
            role: role,
            assign: assign,
            onOk: (game: GameDto) => {
                openLobby()
            }
        })
    }
    return <Stack spacing={2} direction="column">
        <RedirectIfNotLogin/>
        {games.map(game => {
            let userAlreadyAssigned = false
            if (user.logged) {
                userAlreadyAssigned = game.assignments
                    .filter(as => as.user.id === user.id)
                    .length > 0
            }
            
            return <div>
                    <Stack spacing={2} direction="column">
                    <h2>{game.name}</h2>

                    {game.assignments.map(assign => {
                        return <div>
                            {assign.role}: {assign.user.name}
                        </div>
                    })}
                    {userAlreadyAssigned && <Button onClick={createJoinGame(game.id, UserRole.Hunter, false)}>
                        Přidat se do hry
                    </Button>}
                    {!game.hasHunter && <Button disabled={userAlreadyAssigned} onClick={createJoinGame(game.id, UserRole.Hunter, true)}>
                        Připojit se jako lovec
                    </Button>}
                    {game.freeSlots > 0 && <Button disabled={userAlreadyAssigned} onClick={createJoinGame(game.id, UserRole.Player, true)}>
                        Připojit se jako hráč
                    </Button>}
                </Stack>
            </div>
        })}
    </Stack>
}