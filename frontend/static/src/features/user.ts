import { createSlice } from '@reduxjs/toolkit'
import { User } from '../data/User'

export const userSlice = createSlice({
  name: 'user',
  initialState: {
    value: undefined as (User|undefined),
  },
  reducers: {
    setUser: (state, action) => {
      // Redux Toolkit allows us to write "mutating" logic in reducers. It
      // doesn't actually mutate the state because it uses the Immer library,
      // which detects changes to a "draft state" and produces a brand new
      // immutable state based off those changes
      state.value = action.payload
    },
    unsetUser: (state) => {
      state.value = undefined
    }
  },
})

// Action creators are generated for each case reducer function
export const { setUser, unsetUser } = userSlice.actions

export default userSlice.reducer