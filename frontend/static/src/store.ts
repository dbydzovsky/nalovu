import { configureStore } from '@reduxjs/toolkit'
import user from './features/user'
import game from './features/game'

// https://react-redux.js.org/tutorials/quick-start
export default configureStore({
  reducer: {
    user,
    game
  },
})