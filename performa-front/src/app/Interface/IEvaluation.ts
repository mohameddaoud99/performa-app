import { EvaluationCompetency } from "./IEvaluationCompetency";
import { User } from './IUser';


export class Evaluation {
  id: number |null = null;
  evaluationCompetencies:EvaluationCompetency[]=[];
  id_appraiser: number |null = null;
  status: string="";
  submitted:boolean=false;
  reviwerStepeSubmitted:boolean=false;
  user: User=new User();
  final_mark: number  = 0;
  comments: string="";
  objectives: string="";
  dateReviewerEvaluation:Date;
  dateEmployeeSignature:Date;
  dateAppraiserSignature:Date;
  rejection_reasons:String="";
}