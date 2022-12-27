import React from 'react'
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import TextField from '@mui/material/TextField';
import { Logo } from '../components/Logo';



export interface OnSubmitProps {
    username: string
    password: string
}

export interface FormProps {
    onSubmit: (props: OnSubmitProps) => void
    text: string
}

export function UserPassForm(props: FormProps) {
    const [errorMessages, setErrorMessages] = React.useState("");
    const [isSubmitted, setIsSubmitted] = React.useState(false);
    const [pass, setPass] = React.useState("")
    const [user, setUser] = React.useState("")
    
    const handleSubmit = (e:any) => {
        e.preventDefault();
        props.onSubmit({
            password: pass,
            username: user
        })
    }
    const renderForm = (
        <div className="form">
          <h1>{props.text}</h1>
          <form onSubmit={handleSubmit}>
            <Stack spacing={2} direction="column">
                <TextField value={user} 
                      label="Uživatelské jméno"
                        onChange={(e) => {setUser(e.target.value)}} />
                
                <TextField value={pass} 
                      label="Heslo"
                      type="password"
                        onChange={(e) => {setPass(e.target.value)}} />
                <Button type="submit" variant="outlined" >{props.text}</Button>
            </Stack>
            
          </form>
        </div>
      );
    
      return (
        <div className="app">
          <div className="login-form">
            <Logo/>
            { renderForm } 
          </div>
        </div>
      );

}