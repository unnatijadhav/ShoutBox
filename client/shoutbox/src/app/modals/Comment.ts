import { Shout } from './Shouts';
import { User } from './User';

export class Comment{
    commentId:number;
    content:string;
    shout:Shout;
    user:User;
}