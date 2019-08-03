import { User } from './User';

export class Shout{
    id:number;
	timestamp:string;
	isShoutActive:boolean;
	isReported:boolean;
	shoutContentType:string;
	data:any;
	owner:User;
	comments:Comment[];
	
	
}


export class ShoutText{
    id:number;
	timestamp:string;
	isShoutActive:boolean;
	isReported:boolean;
	shoutContentType:string;
	data:string;
	owner:User;
	comments:Comment[];
}