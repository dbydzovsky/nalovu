import { ErrorResponse } from "@remix-run/router";
import axios from "axios";
import { User, UserRole } from "../data/User";
import { updateGame } from "../features/game";
import { setUser } from "../features/user";


function checkForbidden(dispatch: Function, e: any) {
    if (e.response.status === 403) {
        dispatch(setUser(undefined))
    }
}

export interface SendAnswerProps {

}

export async function sendAnswer(props: SendAnswerProps) {
    const response = await axios.post('/api/runtime/answer', props)
    const data = await response.data
}

export interface GetUserProps {

}
export const getUser = () => {
    return async (dispatch: Function) => {
        try {
            const response = await axios.get('/api/user')
            const data = await response.data
            const user: User = {
                id: data.id,
                role: undefined,
                name: data.name
            }
            dispatch(setUser(user))
        } catch(e) {
            dispatch(setUser(undefined))
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
            const response = await axios.post('/api/registration', props.creds)    
            const data = await response.data
            dispatch(setUser(data))
            props.onOk()
        } catch(e: any) {
            checkForbidden(dispatch, e)
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
            const response = await axios.post('/api/signin', data)
            const responseData = await response.data
            dispatch(setUser(responseData))
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
            const response = await axios.post('/api/logout')
            dispatch(setUser(undefined))
        } catch(e) {
            dispatch(setUser(undefined))
        }
    }
}
export interface Group {
    name: string
}
export interface GameAssignment {
    role: UserRole
    user: User
    group: Group
}
export interface GameDto {
    id: number
    name: string
    freeSlots: number
    assignments: GameAssignment[]
    hasHunter: boolean
}
export interface ListGamesProps {
    onDone: (games: GameDto[]) => void
}
export function listGames(props: ListGamesProps) {
    return async function x (dispatch: Function) {
        try {
            const response = await axios.get('/api/game')
            const data = await response.data
            props.onDone(data as GameDto[])
        } catch(e) {
            checkForbidden(dispatch, e)
        }
    }
    
}
export interface JoinGameProps {
    gameId: number,
    role: UserRole,
    assign: boolean
    onOk: (game: GameDto) => void
}
export function joinGame(props: JoinGameProps) {
    return async function x(dispatch: Function) {
        try {
            let url = ''
            if (props.assign) {
                url = '/api/game/join'
            } else {
                url = '/api/game/set'
            }
            const response = await axios.post(url, {
                gameId: props.gameId,
                role: props.role
            })
            const data = await response.data
            props.onOk(data as GameDto)
        } catch(e) {
            checkForbidden(dispatch, e)
        }
    } 
    
}

export function updateGameForClients() {
    return async function x(dispatch: Function) {
        try {
            const response= await axios.post('/api/runtime/update')
            const data = await response.data
        } catch(e ) {
            checkForbidden(dispatch, e)
        }
    }
}

async function callPost(dispatch: Function, url: string, data: any) {
    try {
        const response = await axios.post(url, data)
        return await response.data
    }catch(e) {
        checkForbidden(dispatch, e)
        throw new Error("Unexpected error: " + e)
    }
}

export interface StartMoneyFightAvailablesProps {
    onOk: (users: User[]) => void
}
export function getStartMoneyAvailables(props: StartMoneyFightAvailablesProps) {
    return async function x (dispatch: Function) {
        const data = await callPost(dispatch, "/api/runtime/moneyfight/available", {})
        props.onOk(data)
    }
}

export interface CorrectAnswerProps {
    userId: number
    questionId: number
    answerId?: number
}
export function correctAnswer(props :CorrectAnswerProps) {
    return async function x(dispatch: Function) {
        await callPost(dispatch, "/api/runtime/moneyfight/correct", props)    
    }
}
export interface StartMoneyFightProps {
    userId: number
}
export function startMoneyFight(props :StartMoneyFightProps) {
    return async function x(dispatch: Function) {
        await callPost(dispatch, "/api/runtime/moneyfight/start", props)    
    }
}