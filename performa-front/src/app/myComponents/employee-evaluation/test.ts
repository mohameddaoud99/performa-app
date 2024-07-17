import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/Interface/IUser';
import { UserService } from 'src/app/_services/user.service';
import { StorageService } from '../../_services/storage.service';
import { EvaluationService } from '../../_services/evaluation.service';
import { CamundaApiService } from 'src/app/_services/camunda-api.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-employee-evaluation',
  templateUrl: './employee-evaluation.component.html',
  styleUrl: './employee-evaluation.component.scss'
})
export class EmployeeEvaluationComponent {

  constructor(private userService: UserService,
    private camundaApiService: CamundaApiService,
    private evaluationService: EvaluationService,
    private storageService: StorageService, 
    private toastrService: ToastrService,
    private router: Router,
      ) {
}

 emailPresentUser:any;
 presentUser:any;
 category_competenciesList:any;
 competenciesList:any;
 appraiserRating: any;
 updatedCompetenciesList: any[] = [];
 ngOnInit():void{
  this.getUserByEmail();   
 }


/////////////////////////////  get Present User function ////////////////////////////////////////
getUserByEmail() {
    this.emailPresentUser = this.storageService.getUser().email;
    this.userService.getUserByEmail(this.emailPresentUser).subscribe(data => {
    this.presentUser = data;
    console.log(this.presentUser);
    this.category_competenciesList=this.presentUser.job.categoryCompetencies
    console.log(this.category_competenciesList);
    this.competenciesList=this.category_competenciesList[0].competencies
    console.log(this.competenciesList);
  });
}

  // Method to update the employee rating and calculate points
  updateEmployeeRating(event: any, competency: any) {
    const employeeRating = parseFloat(event.target.value); // Get the selected employee rating
    const appraiserRating = competency.appraiserRating; // Get the appraiser rating
    const weightage = competency.weightage; // Get the weightage

    // Calculate updated points for the competency
    const points = this.calculatePoints(weightage, appraiserRating);

    // Update the competency object
    const updatedCompetency = {
      id: competency.id,
      employeeRating: employeeRating,
      appraiserRating: appraiserRating,
      employeeComment: competency.employeeComment,
      appraiserComment: competency.appraiserComment,
      points: points
    };

    // Check if the competency is already in the list
    const existingIndex = this.updatedCompetenciesList.findIndex((c: any) => c.id === competency.id);
    if (existingIndex !== -1) {
      // If the competency exists in the list, update its details
      this.updatedCompetenciesList[existingIndex] = updatedCompetency;
    } else {
      // If the competency doesn't exist in the list, add it
      this.updatedCompetenciesList.push(updatedCompetency);
    }

    console.log('Updated competency:', updatedCompetency);
  }

  // Method to update the appraiser rating
  updateAppraiserRating(event: any, competency: any) {
    const appraiserRating = parseFloat(event.target.value); // Get the selected appraiser rating
    const employeeRating = competency.employeeRating; // Get the employee rating
    const weightage = competency.weightage; // Get the weightage

    // Calculate updated points for the competency
    const points = this.calculatePoints(weightage, appraiserRating);

    // Update the competency object
    const updatedCompetency = {
      id: competency.id,
      employeeRating: employeeRating,
      appraiserRating: appraiserRating,
      employeeComment: competency.employeeComment,
      appraiserComment: competency.appraiserComment,
      points: points
    };

    // Check if the competency is already in the list
    const existingIndex = this.updatedCompetenciesList.findIndex((c: any) => c.id === competency.id);
    if (existingIndex !== -1) {
      // If the competency exists in the list, update its details
      this.updatedCompetenciesList[existingIndex] = updatedCompetency;
    } else {
      // If the competency doesn't exist in the list, add it
      this.updatedCompetenciesList.push(updatedCompetency);
    }

    console.log('Updated competency:', updatedCompetency);
  }

  // Method to calculate points for a competency
  calculatePoints(weightage: number, appraiserRating: number): number {
    // Adjust the calculation based on your requirements
    return (weightage * appraiserRating) / 100; // Example calculation
  }




}


