import React from 'react'
import { login } from '../action/api'

export function Login () {
    const [errorMessages, setErrorMessages] = React.useState({});
    const [isSubmitted, setIsSubmitted] = React.useState(false);
    const [pass, setPass] = React.useState("")
    const [user, setUser] = React.useState("")
    
    const handleSubmit = (e:any) => {
        e.preventDefault();
        login({
            password: pass,
            username: user
        })
    }
    const renderForm = (
        <div className="form">
            Login
          <form onSubmit={handleSubmit}>
            <div className="input-container">
              <label>Username </label>
              <input type="text" value={user} onChange={(e) => {setUser(e.target.value)}} name="username" required />
            </div>
            <div className="input-container">
              <label>Password </label>
              <input type="password" value={pass} onChange={(e) => {setPass(e.target.value)}}  name="password" required />
            </div>
            <div className="button-container">
              <input type="submit" />
            </div>
          </form>
        </div>
      );
    
      return (
        <div className="app">
          <div className="login-form">
            <div className="title">Sign In</div>
            {isSubmitted ? <div>User is successfully logged in</div> : renderForm}
          </div>
        </div>
      );

}