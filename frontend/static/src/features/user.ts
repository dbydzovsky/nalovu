import { createSlice } from '@reduxjs/toolkit'
import { User } from '../data/User'

export interface MyUser extends User{
  logged: boolean
  loaded: boolean
}

export const userSlice = createSlice({
  name: 'user',
  initialState: {
    value: {logged: false, loaded: false} as MyUser,
  },
  reducers: {
    setUser: (state, action) => {
      // Redux Toolkit allows us to write "mutating" logic in reducers. It
      // doesn't actually mutate the state because it uses the Immer library,
      // which detects changes to a "draft state" and produces a brand new
      // immutable state based off those changes
      state.value = {...action.payload, logged: action.payload !== undefined, loaded: true} as MyUser
    },
    unsetUser: (state) => {
      state.value = {logged: false, loaded: true} as MyUser
    }
  },
})

// Action creators are generated for each case reducer function
export const { setUser, unsetUser } = userSlice.actions

export default userSlice.reducer