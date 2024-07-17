import { Component, OnInit } from '@angular/core';
import { DashboardService } from 'src/app/_services/dashboard.service';
import { StorageService } from '../../_services/storage.service';
import { User } from 'src/app/Interface/IUser';
import { UserService } from 'src/app/_services/user.service';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor(
    private userService: UserService,
    private dashboardService: DashboardService,
    private storageService: StorageService,
  ) { }

  userRole: string = "";
  presentUserId: number;
  presentUser: User;
  evaluationsCountByEmployee: string = "0"; 
  competenciesCountByEmployee: string = "0"; 
  dateList: string[] = [];
  marksList: any[] = [];
  dataBarChart: any;
  statusList: any[] = [];
  employeeCountList: any[] = [];
  dataPieChart: any;
  adminDataPieChart:any
  selectedFilterOption:String="DEPARTMENT";


  ngOnInit(): void {
    this.presentUserId = this.storageService.getUser().id;
    this.userRole = this.storageService.getUser().role;
    this.getUserById(this.presentUserId);
    this.getEvaluationsCountByEmployee(this.presentUserId);
    this.getEvaluationsDataByEmployee(this.presentUserId);
    this.getEmployeeNumberByStatusByAppraiser(this.presentUserId);
    this.getAllEmployeeNumberByStatus();
    this.getAllDepartments();
    this.getAllRoles();
  }

  getUserById(id: any): void {
    this.userService.getUserById(id).subscribe(data => {
      this.presentUser = data;
      this.getProfileCompetenciesCountById(this.presentUser.profile.id);
    });
  }

  ////////////////////////////////////////////////////////////////
  // Employee dashboard cards
  ////////////////////////////////////////////////////////////////

  getEvaluationsCountByEmployee(employee_id: number): void {
    this.dashboardService.getEvaluationsCountByEmployee(employee_id).subscribe(data => {
      this.evaluationsCountByEmployee = String(data);
    });
  }

  getProfileCompetenciesCountById(profile_id: any): void {
    this.dashboardService.getProfileCompetenciesCountById(profile_id).subscribe(data => {
      this.competenciesCountByEmployee = String(data);
    });
  }

  ////////////////////////////////////////////////////////////////
  // Employee Bar chart
  ////////////////////////////////////////////////////////////////

  getEvaluationsDataByEmployee(employee_id: any): void {
    this.dashboardService.getEvaluationsDataByEmployee(employee_id).subscribe(data => {
      
      this.dateList = data.dateList;
      this.marksList = data.marksList;
      this.updateDataBarChart();
    }, error => {
      console.error('Error fetching evaluations data', error);
    });
  }

  updateDataBarChart(): void {
    this.dataBarChart = {
      labels: this.dateList,
      datasets: [
        {
          label: 'Mark  ',
          backgroundColor: '#f87979',
          data: this.marksList,
        }
      ]
    };
  }



//////////////////////////////////////////////// Employee Pie chart //////////////////////////////////////////////////

getEmployeeNumberByStatusByAppraiser(appraiser_id: any): void {
  this.dashboardService.getEmployeeNumberByStatusByAppraiser(appraiser_id).subscribe(data => {    
    this.statusList = data.statusList;
    this.employeeCountList = data.employeeCountList;

    this.updateDataPieChart();
  }, error => {
    console.error('Error fetching evaluations data', error);
  });
}

updateDataPieChart(): void {
  this.dataPieChart = {
    labels: this.statusList,

    datasets: [{
          backgroundColor: ['#41B883','#E46651', '#00D8FF', '#DD1B16'],
          data: this.employeeCountList,
          innerHeight:5
        },
      ]
  };
}

//////////////////////////////////////////////// Admin Dashboard//////////////////////////////////////////////////

allStatusList:string="";
allEmployeeCountList:string="";
getAllEmployeeNumberByStatus(): void {
  this.dashboardService.getAllEmployeeNumberByStatus().subscribe(data => {
    this.allStatusList = data.statusList;
    this.allEmployeeCountList = data.employeeCountList;
    this.updateDataAdminPieChartAllEmployeeCount();
  }, error => {
    console.error('Error fetching evaluations data', error);
  });
}
updateDataAdminPieChartAllEmployeeCount(): void {
  this.adminDataPieChart = {
    labels: this.allStatusList,
    datasets: [{
          backgroundColor: ['#41B883', '#E46651', '#00D8FF', '#DD1B16'],
          data: this.allEmployeeCountList,
          innerHeight:5
        },
      ]
  };
}

statusListByDepartment:string="";
employeeCountListByDepartment:string="";
getEmployeeNumberByStatusByDepartment(department: string): void {
  this.dashboardService.getEmployeeNumberByStatusByDepartment(department).subscribe(data => {
    this.statusListByDepartment = data.statusList;
    this.employeeCountListByDepartment = data.employeeCountList;
    this.updateDataAdminPieChartByDepartment();
  }, error => {
    console.error('Error fetching evaluations data', error);
  });
}
updateDataAdminPieChartByDepartment(): void {
  this.adminDataPieChart = {
    labels: this.statusListByDepartment,
    datasets: [{
          backgroundColor: ['#41B883', '#E46651', '#00D8FF', '#DD1B16'],
          data: this.employeeCountListByDepartment,
          innerHeight:5
        },
      ]
  };
}

statusListByRole:string="";
employeeCountListByRole:string="";
getEmployeeNumberByStatusByRole(role: string): void {
  this.dashboardService.getEmployeeNumberByStatusByRole(role).subscribe(data => {
    this.statusListByRole = data.statusList;
    this.employeeCountListByRole = data.employeeCountList;
    this.updateDataAdminPieChartByRole();
  }, error => {
    console.error('Error fetching evaluations data', error);
  });
}
updateDataAdminPieChartByRole(): void {
  this.adminDataPieChart = {
    labels: this.statusListByRole,
    datasets: [{
          backgroundColor: ['#E46651', '#41B883', '#00D8FF', '#DD1B16'],
          data: this.employeeCountListByRole,
          innerHeight:5
        },
      ]
  };
}

/////////////////////////////
departmentsList:any[];
rolesList:any[];
getAllDepartments() {
  return this.userService.getAllDepartments().subscribe(data => {
    this.departmentsList = data.data;     
  })
}
getAllRoles() {
  return this.userService.getAllRoles().subscribe(data => {
    this.rolesList = data;     
  })
}

onDepartmentChange(event: any) {
  const departmentName = event.target.value;
  if (departmentName === 'ALL DEPARTMENTS') {
    this.getAllEmployeeNumberByStatus();
  } else {
    this.getEmployeeNumberByStatusByDepartment(departmentName);
  }
}
onRoleChange(event: any) {
  const roleName = event.target.value;
  if (roleName === 'ALL ROLES') {
    this.getAllEmployeeNumberByStatus();
  } else {
    this.getEmployeeNumberByStatusByRole(roleName);
  }
}



//////////////////////////////////////////////// chartOptions //////////////////////////////////////////////////
  chartOptions = {
    maintainAspectRatio: false,
    plugins: {
      legend: {
        display: true
      },
      tooltip: {
        callbacks: {
          labelColor: function(context: any) {
            return {
              backgroundColor: context.dataset.borderColor || context.dataset.backgroundColor,
              borderColor: context.dataset.borderColor || context.dataset.backgroundColor
            };
          }
        }
      }
    },
    // scales: {
    //   x: {
    //     grid: {
    //       drawOnChartArea: false
    //     }
    //   },
    //   y: {
    //     beginAtZero: true,
    //     max: 250,
    //     ticks: {
    //       maxTicksLimit: 5,
    //       stepSize: Math.ceil(250 / 5)
    //     }
    //   }
    // },
    elements: {
      line: {
        tension: 0.4
      },
      point: {
        radius: 0,
        hitRadius: 10,
        hoverRadius: 4,
        hoverBorderWidth: 3
      }
    }
  };
}
