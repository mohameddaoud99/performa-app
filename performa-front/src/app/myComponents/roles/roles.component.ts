import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { RoleService } from '../../_services/role.service';
import { ToastrService } from 'ngx-toastr';
import { Permission } from 'src/app/Interface/IPermission';
import { Role } from 'src/app/Interface/IRole';
import { DataTableDirective } from 'angular-datatables';
import { from, Subject } from "rxjs";
import { switchMap, tap } from "rxjs/operators";
@Component({
  selector: 'app-roles',
  templateUrl: './roles.component.html',
  styleUrl: './roles.component.scss'
})
export class RolesComponent implements AfterViewInit, OnDestroy, OnInit{

  @ViewChild(DataTableDirective, {static: false}) datatableElement: DataTableDirective;
  dtTrigger: Subject<any> = new Subject<any>();  
  form: any = {
    roleName: null,
    color:null,
    permissions: []
  };

  roleToUpdate: Role = new Role();

  formToUpdate: any = {
    name: this.roleToUpdate.name,
    coor : this.roleToUpdate.color,
    permissions: this.roleToUpdate.permissions
  }

  showlist:boolean=false;
  RolesList: Role[];
  permission: Permission[] = [];
  responseInfo: any;
  toastMessage: any;
  submitted: boolean = false;
  public popAddVisibale = false;
  public toastVisibility = false
  user: any;
  dtoptions: DataTables.Settings = {};
  deleteModalVisibility = false;
  selectedRoleId: number | null = null;
  popUpdateVisible :boolean = false;



  constructor(private roleService: RoleService, private toastrService: ToastrService) { }


  ngOnInit(): void {
    this.dtoptions = {
      pagingType: 'full_numbers'
    };
  }

  ngAfterViewInit(): void {
    this.roleService.getAllRoles().subscribe(data => {
      this.RolesList = data;
      this.dtTrigger.next(null);
      this.showlist=true;  
    })
  }

  getCurrentPrem(p: Permission){
    return this.roleToUpdate.permissions.includes(p);
  }


  ///////////////////////////// Modal Add user functions ///////////////////////////////////

  toggleAddModal() {
    this.popAddVisibale = !this.popAddVisibale;
    this.getAllPermissions();
    this.resetAddRoleForm();
  }

  ///////////////////////////// Modal Confirm delete role functions ///////////////////////////////////

  handleDeleteModal(event: any) {
    this.deleteModalVisibility = event;
  }

  toggleDeleteModal(roleId: number | null): void {
    this.selectedRoleId = roleId;
    this.deleteModalVisibility = !this.deleteModalVisibility;
  }
  toggleCloseDeleteModal(): void {

    this.deleteModalVisibility = !this.deleteModalVisibility;
  }


  ///////////////////////////// Modal Update role functions ///////////////////////////////////

  handleUpdateModal(event: any) {
    this.popUpdateVisible = event;
  }

  toggleUpdateModal(roleId: number | null): void {
    this.selectedRoleId = roleId;
    this.getRoleById(this.selectedRoleId)
  }

  closePopUpdate(): void {

    this.popUpdateVisible = !this.popUpdateVisible;
  }

  ///////////////////////////// Toast Role functions ///////////////////////////////////

  showNotification(message:string){
    this.toastrService.success('Success', message, {
      timeOut: 5000,
    });
  }

  /////////////////////////////  add Role function ////////////////////////////////////////

  onSubmit(p: any): void {
    this.submitted = true;
    
    
    const { roleName, permissions ,color } = this.form;
    this.roleService.createRole(roleName,color, permissions).subscribe({
      next: addRoleData => {
          this.toastrService.success("Success",addRoleData.data.body.message);
          this.resetAddRoleForm();
          this.toggleAddModal();
          this.getAllRoles();
      },
      error: err => {
        this.toastrService.warning("Attention !", err.error.message);
      }
    });
  }


  ///////////////////////////// reset add Role form function /////////////////////////////

  resetAddRoleForm(): void {
    this.form = {
      roleName: null,
    };
    this.submitted = false;
  }

  /////////////////////////////  get all roles function ////////////////////////////////////////

  getAllRoles() {
    from(this.datatableElement.dtInstance)
    .pipe(
      tap((dtInstance: DataTables.Api) => { dtInstance.destroy(); this.showlist=false; }),
      switchMap(() => 
        this.roleService.getAllRoles()
      )
    )
    .subscribe(data => {
      this.RolesList = data;
      this.dtTrigger.next(null);
      this.showlist=true;
    });
  }

  /////////////////////////////  get Role By Id function ////////////////////////////////////////////

  getRoleById(id: any) {
    this.roleService.getRoleById(id).subscribe(data => {
      this.roleToUpdate = data;
      this.getAllPermissions();
      this.popUpdateVisible=true;
      
    })
  }
  isPermissionSelected(perm:Permission): boolean {
    
    if (this.roleToUpdate && this.roleToUpdate.permissions) {
      
      return true;
    }
    return false;
  }

  /////////////////////////////////  Update role function ///////////////////////////////////////////

  updateRole(): void {
    this.submitted = true;
    this.roleService.updateRole(this.roleToUpdate).subscribe({
      next: data => {
          this.closePopUpdate();
          this.submitted = false;
          this.popAddVisibale=false;
          this.getAllRoles();
          this.showNotification("Role updated successfully");
      },
      error: err => {
        this.toastrService.warning("Attention !",err.error.message);
      }
    });
  }


  ////////////////////////////////  delete role function ////////////////////////////////////////////

  deleteRole() {
    if (this.selectedRoleId !== null) {
      this.roleService.deleteRole(this.selectedRoleId).subscribe(data => {
        this.getAllRoles();
        this.showNotification('Role deleted successfully');
        this.selectedRoleId = null;
        this.toggleDeleteModal(null);
      })
    }
  }

  /////////////////////////////  get all permissions function ////////////////////////////////////////

  getAllPermissions() {
    return this.roleService.getAllPermissions().subscribe(data => {     
      this.permission = data;
    })
  }



  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

}




