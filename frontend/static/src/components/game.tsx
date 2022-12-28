import React from 'react'
import { useSelector } from 'react-redux'
import { GameRuntime } from '../features/game'



export function useGameRuntime(): GameRuntime {
    const game: GameRuntime = useSelector((state: any) => state.game.value)
    return game
}