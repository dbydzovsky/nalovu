
export enum UserRole {
    Admin = "Admin",
    Hunter = "Hunter",
    Player = "Player",
}
export interface User {
    role: UserRole | undefined
    name: string
}