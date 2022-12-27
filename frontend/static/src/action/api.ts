import axios from "axios";
import { User, UserRole } from "../data/User";
import { setUser } from "../features/user";


export interface SendAnswerProps {
    gameId: number
}

export async function sendAnswer(props: SendAnswerProps) {
    const response = await axios.post('/api/game/answer', props)
}

export interface GetUserProps {

}
export const getUser = () => {
    return async (dispatch: Function) => {
        try {
            const response = await axios.get('/api/user')
            const data = await response.data
            const user: User = {
                role: undefined,
                name: data.name
            }
            dispatch(setUser(user))
        } catch(e) {
            console.log(e)
        }
    } 
}


export interface RegisterProps {
    creds: Credentials
    onOk: () => void
}
export function register(props: RegisterProps) {
    return async function x(dispatch: Function) {
        try {
            const response = await axios.post('/registration', props.creds)    
            props.onOk()
        } catch(e) {
            console.log(e)
        }
    }

}

export interface Credentials {
    username: string
    password: string
}

export interface LoginProps {
    creds: Credentials
    onOk: () => void
}
export function login(props: LoginProps) {
    return async function x(dispatch: Function) {
        const data = {
            ...props.creds,
            'login-submit':	"Log+In"
        }
        try {
            const response = await axios.post('/signin', data)
            props.onOk()
        } catch(e ) {

        }
    }

}

export interface LogoutProps {
    onDone: () => void
}

export function logout(props: LogoutProps) {
    return async function (dispatch: Function ) {
        try { 
            const response = await axios.post('/logout')
            dispatch(setUser(undefined))
        } catch(e) {
            console.log(e)
        }
    }
}
export interface GameDto {
    id: number
    name: string
    hasHunter: boolean
}
export interface ListGamesProps {
    onDone: (games: GameDto[]) => void
}
export async function listGames(props: ListGamesProps) {
    try {
        const response = await axios.get('/api/game')
        const data = await response.data
        props.onDone(data as GameDto[])
    } catch(e) {
        console.log(e)
    }
}
export interface JoinGameProps {
    gameId: number,
    role: UserRole,
    onOk: (game: GameDto) => void
}
export async function joinGame(props: JoinGameProps) {
    try {
        const response = await axios.post('/api/game/join', {
            gameId: props.gameId,
            role: props.role
        })
        const data = await response.data
        props.onOk(data as GameDto)
    } catch(e) {
        console.log(e)
    }
}