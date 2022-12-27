
export enum UserRole {
    Admin = "Admin",
    Hunter = "Hunter",
    Player = "Player",
}
export interface User {
    id: number
    role: UserRole | undefined
    name: string
}