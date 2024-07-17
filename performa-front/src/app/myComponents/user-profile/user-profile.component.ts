import { Component } from '@angular/core';
import { StorageService } from '../../_services/storage.service';
import { User } from 'src/app/Interface/IUser';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent {

  constructor(
    private userService: UserService,
    private storageService: StorageService,
  ) { }

  progressValue: number = 80;
  presentUserId:number;
  presentUser: User;
  averageMarksList:any;


  ngOnInit(): void {
    this.presentUserId = this.storageService.getUser().id;
    this.getUserById(this.presentUserId);
    this.getAverageMarksForEachCompetencyPerCategoryPerUser(this.presentUserId);
  }

  getUserById(id: any): void {
    this.userService.getUserById(id).subscribe(data => {
      this.presentUser = data;
    });
  }

  getAverageMarksForEachCompetencyPerCategoryPerUser(id: any): void {
    this.userService.getAverageMarksForEachCompetencyPerCategoryPerUser(id).subscribe(data => {
      this.averageMarksList = data;
    });
  }


}
