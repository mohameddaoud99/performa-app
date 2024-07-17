import { User } from './IUser';

export class EmployeeEvaluationRequest {
  
  appraiser: User |null = null;
  status: string="";
  date:Date=new Date();

}