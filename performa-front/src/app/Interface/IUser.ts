import { Role } from "./IRole";
import {Grade} from "./IGrade";
import {Profile} from "./IProfile";

    
  export class User {
    id: number |null =null;
    firstname: string ="";
    lastname: string="";
    email: string="";
    password: string="";
    role: Role;
    department: string="";
    grade!: Grade;
    profile!: Profile;
    team:string="";
    birthday: Date=new Date();
    status:string ="";
    id_appraiser: number |null =null;
  }

export class Permission{

}


    