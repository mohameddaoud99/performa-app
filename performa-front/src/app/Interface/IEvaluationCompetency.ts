import { Competency } from "./ICompetency";
export class EvaluationCompetency {
    id: number |null = null;
    competency: Competency = new Competency();
    employee_mark: number  = 0;
    appraiser_mark: number  = 0;
    employee_comment: string= "";
    appraiser_comment: string= "";
    level: string= "";
    weightage: number  = 0;
    selected: boolean=true;
    points: number  = 0;
}