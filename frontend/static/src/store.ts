import { configureStore } from '@reduxjs/toolkit'
import user from './features/user'

// https://react-redux.js.org/tutorials/quick-start
export default configureStore({
  reducer: {
    user
  },
})