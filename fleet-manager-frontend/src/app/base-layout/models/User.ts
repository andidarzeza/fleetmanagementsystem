import {Role} from "../../role.enum";

export class User{
    username: string;
    token: string;
    role: Role;
    constructor(username, token) {
        this.username = username;
        this.token = token;
    }

}
