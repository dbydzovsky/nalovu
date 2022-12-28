import React from 'react'
import { User } from '../../data/User'
import { GameRuntime } from '../../features/game'
import { useGameRuntime } from '../game'
import { useUser } from '../user'
import { GameBar } from './Bar'
import { useGameUser } from './gameUser'
import { chooseScreen } from './screenChooser'

export interface GameProps {

}

export interface GenericGameProps {
    game: GameRuntime
    user: User
}

export function Game(props: GameProps) {
    const game = useGameRuntime()
    const user = useGameUser()
    const genericProps: GenericGameProps = {
        game, user
    }
    const Component = chooseScreen(genericProps)
    return <>
      <GameBar {...genericProps}/>
      <Component {...genericProps}/>
    </>
}