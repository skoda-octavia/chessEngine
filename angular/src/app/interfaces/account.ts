export interface Account {
    id: number,
    username: String,
    email: String,
    password: String,
    locked: boolean,
    enabled: boolean,
    accountRole: String
}