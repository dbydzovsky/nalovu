import axios from "axios";
import { getBaseUrl } from "../utils/Web";


export interface SendAnswerProps {
    gameId: number
}

export async function sendAnswer(props: SendAnswerProps) {
    const response = await axios.post(getBaseUrl() + '/api/game/answer', props)
}

export interface GetUserProps {

}
export async function getUser() {
    axios.get(getBaseUrl() + '/api/user')
    .then(async (response)=> {
        const data = await response.data
        console.log(data)
    }).catch(error => {
        console.log(error)
    })
}


export interface RegisterProps {
    username: string
    password: string
}
export async function register(props: RegisterProps) {
    axios.post(getBaseUrl() + '/registration', props)
}

export interface LoginProps {
    username: string
    password: string
}
export async function login(props: LoginProps) {
    const data = {
        ...props,
        'login-submit':	"Log+In"
    }
    axios.post(getBaseUrl() + '/signin', data)
}