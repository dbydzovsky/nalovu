import { configureStore } from '@reduxjs/toolkit'
import counterReducer from './features/counter'

// https://react-redux.js.org/tutorials/quick-start
export default configureStore({
  reducer: {
    counterReducer
  },
})