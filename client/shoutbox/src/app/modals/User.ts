import { Shout } from './Shouts';
import { Role } from './Role';

export class User{

    id:number;
    firstName:string;
    lastName:string;
    dateOfBirth:string;
    email:string;
    password:string;
    gender:string;
    role:Role;
    isUserActive:boolean; 
    shouts:Shout[]; 

}
