import React from 'react'
import { register } from '../action/api'

export function Register () {
    const [errorMessages, setErrorMessages] = React.useState({});
    const [isSubmitted, setIsSubmitted] = React.useState(false);
    const [pass, setPass] = React.useState("")
    const [user, setUser] = React.useState("")
    
    const handleSubmit = (e:any) => {
        e.preventDefault();
        register({
            username: user,
            password: pass
        })
    }
    const renderForm = (
        <div className="form">
            Register
          <form onSubmit={handleSubmit}>
            <div className="input-container">
              <label>Username </label>
              <input type="text" name="username" value={user} onChange={(e) => {setUser(e.target.value)}}  required />
            </div>
            <div className="input-container">
              <label>Password </label>
              <input type="password" name="password" value={pass} onChange={(e) => {setPass(e.target.value)}}  required />
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