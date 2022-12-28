import React from 'react'
import { User } from '../../data/User';
import { useGameRuntime } from '../game';
import { useUser } from '../user';

export function useGameUser(): User {
    const game = useGameRuntime()
    const user = useUser()
    if (!game.loaded) {
        return user
    }
    if (user.id === game.admin.id) {
        return game.admin
    } else if (user.id === game.hunter.id) {
        return game.hunter
    }
    const found = game.players.filter(player=>player.id === user.id)
    if (found.length !== 1) {
        throw new Error("Player not found")
    }
    return found[0]
}