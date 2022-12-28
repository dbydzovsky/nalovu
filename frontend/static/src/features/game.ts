import { createSlice } from '@reduxjs/toolkit'
import { User, UserRole } from '../data/User'

export interface GameUser{
    id: number
    name: string,
    role: UserRole
}

export interface GameRuntime {
  name: string
  admin: GameUser
  hunter: GameUser
  players: GameUser[]
  amount: number
  
  loaded: boolean
}

export const gameSlice = createSlice({
  name: 'game',
  initialState: {
    value: {loaded: false} as GameRuntime,
  },
  reducers: {
    updateGame: (state, action) => {
      // Redux Toolkit allows us to write "mutating" logic in reducers. It
      // doesn't actually mutate the state because it uses the Immer library,
      // which detects changes to a "draft state" and produces a brand new
      // immutable state based off those changes
      state.value = {...action.payload, loaded: action.payload !== undefined} as GameRuntime
    },
    unsetGame: (state) => {
      state.value = {loaded: false} as GameRuntime
    }
  },
})

// Action creators are generated for each case reducer function
export const { updateGame, unsetGame } = gameSlice.actions

export default gameSlice.reducer