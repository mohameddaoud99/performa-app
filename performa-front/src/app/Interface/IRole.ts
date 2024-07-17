import { Permission } from './IPermission';
export class Role{
    id:number |null =null;
    name:string ="";
    color:string ="";
    permissions:Permission[]=[]
    
}