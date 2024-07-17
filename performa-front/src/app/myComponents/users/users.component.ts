import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/Interface/IUser';
import { Profile } from 'src/app/Interface/IProfile';
import { DataTableDirective } from 'angular-datatables';
import { from, Subject } from "rxjs";
import { switchMap, tap } from "rxjs/operators";
import { Role } from 'src/app/Interface/IRole';
import { Grade } from 'src/app/Interface/IGrade';
import { UserService } from 'src/app/_services/user.service';
@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss'] // Fix typo in styleUrls
})
export class UsersComponent implements AfterViewInit, OnDestroy, OnInit {
  @ViewChild(DataTableDirective, { static: false }) datatableElement: DataTableDirective;
  dtTrigger: Subject<any> = new Subject<any>();
  userToUpdate: User;
  form: User = new User();
  departmentsList: any;
  profilesList: Profile[] = [];
  UsersList: User[] = [];
  RolesList: Role[] = [];
  gradesList: Grade[] = [];
  submitted: boolean = false;
  public visible = false;
  showlist: boolean = false;
  dtoptions: DataTables.Settings = {};
  deleteModalVisibility = false;
  updateModalVisibility = false;
  selectedUserId: number | null = null;
  supervisors: User[] = [];

  constructor(private userService: UserService, private toastrService: ToastrService,) { }

  ngOnInit(): void {
    this.dtoptions = {
      pagingType: 'full_numbers'
    };
    this.getAllDepartments();
    this.getAllProfiles();
    this.getAllRoles();
    this.getAllGrades();
    this.getSupervisors();
  }

  ngAfterViewInit(): void {
    this.userService.getAllUsers().subscribe(data => {
      this.UsersList = data.data;
      this.dtTrigger.next(null);
      this.showlist = true;
    })
  }

  showNotif(message: string) {
    this.toastrService.success('Success', message, {
      timeOut: 5000,
    });
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }


/*
/
/////////////////////////////////// Crud function ////////////////////////////////
/
*/


  getAllUsers() {
    from(this.datatableElement.dtInstance)
      .pipe(
        tap((dtInstance: DataTables.Api) => { dtInstance.destroy(); this.showlist = false; }),
        switchMap(() =>
          this.userService.getAllUsers()
        )
      )
      .subscribe(data => {
        this.UsersList = data.data;
        this.dtTrigger.next(null);
        this.showlist = true;
      });
  }

  getAllDepartments() {
    this.userService.getAllDepartments().subscribe(data => {
      this.departmentsList = data.data;
    })
  }

  getAllProfiles() {
    this.userService.getAllProfiles().subscribe(data => {
      this.profilesList = data.data;
    })
  }

  getUserById(id: any) {
    this.userService.getUserById(id).subscribe(data => {
      this.userToUpdate = data
    })
  }

  getAllRoles() {
    return this.userService.getAllRoles().subscribe(data => {
      this.RolesList = data;
    })
  }

  getAllGrades() {
    return this.userService.getAllGrades().subscribe(data => {
      this.gradesList = data.data;
    })
  }

  getSupervisors() {
    this.userService.getAllAppraisers().subscribe(data => {
      this.supervisors = data.data;
    });
  }

  onSubmit(): void {
    this.submitted = true;
    this.userService.createUser(this.form).subscribe({
      next: adduserdata => {
        if (adduserdata.data.statusCodeValue != 200) {
          this.toastrService.error("Error", adduserdata.data.body.message);
        } else {
          this.closeAddModal();
          this.getAllUsers();
          this.showNotif(adduserdata.message);
        }
      },
      error: err => {
      }
    });
  }

  updateUser() {
    this.submitted = true;
    this.userService.updateUser(this.userToUpdate).subscribe(data => {
      this.getAllUsers();
      this.closeUpdateModal();
      this.showNotif(data.message)
      this.submitted = false;
    })
  }

  resetAddUserForm(): void {
    this.form = new User();
    this.submitted = false;
  }

  deleteUser() {
    if (this.selectedUserId !== null) {
      this.userService.deleteUser(this.selectedUserId).subscribe(data => {
        this.getAllUsers();
        this.selectedUserId = null;
        this.toggleDeleteModal(null);
        this.showNotif('User deleted successfully');
      })
    }
  }

/*
/
///////////////////////////////////////// PopUp function ////////////////////////////////////////
/
*/

  openUpdateModal(userId: number | null): void {
    this.selectedUserId = userId;
    this.getUserById(this.selectedUserId);
    this.updateModalVisibility = true;
  }

  closeUpdateModal(): void {
    this.updateModalVisibility = false;
  }

  closeAddModal() {
    this.visible = false;
    this.resetAddUserForm();
  }

  openAddModal() {
    this.visible = true;
  }


  handleDeleteModal(event: any) {
    this.deleteModalVisibility = event;
  }

  toggleDeleteModal(userId: number | null): void {
    this.selectedUserId = userId;
    this.deleteModalVisibility = !this.deleteModalVisibility;
  }

  toggleCloseDeleteModal(): void {
    this.deleteModalVisibility = !this.deleteModalVisibility;
  }

}
