import { NgModule } from '@angular/core';
import { HashLocationStrategy, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { BrowserModule, Title } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';

import { NgScrollbarModule } from 'ngx-scrollbar';

// Import routing module
import { AppRoutingModule } from './app-routing.module';

// Import app component
import { AppComponent } from './app.component';

// Import containers
import { DefaultFooterComponent, DefaultHeaderComponent, DefaultLayoutComponent } from './containers';
import   {UsersComponent} from './myComponents/users/users.component';
import { DataTablesModule } from 'angular-datatables';
import { FormsModule }   from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';


import {
  AvatarModule,
  BadgeModule,
  BreadcrumbModule,
  ButtonGroupModule,
  ButtonModule,
  CardModule,
  DropdownModule,
  FooterModule,
  FormModule,
  GridModule,
  HeaderModule,
  ListGroupModule,
  NavModule,
  ProgressModule,
  SharedModule,
  SidebarModule,
  TableModule,
  TabsModule,
  UtilitiesModule,
  ModalModule,
  AlertModule,
  ToastModule,
  SpinnerModule,
  ColComponent,
  WidgetStatEComponent,

  
} from '@coreui/angular';

import { ChartjsComponent } from '@coreui/angular-chartjs';



import { IconModule, IconSetService } from '@coreui/icons-angular';
import { LoginComponent } from './myComponents/login/login.component';
import { RolesComponent } from './myComponents/roles/roles.component';
import { NotifyUsersComponent } from './myComponents/notify-users/notify-users.component';
import { EmployeeStatusComponent } from './myComponents/employee-status/employee-status.component';
import { EmployeeEvaluationListComponent } from './myComponents/employee-evaluation-list/employee-evaluation-list.component';
import { UpdateEmloyeeEvaluationComponent } from './myComponents/update-emloyee-evaluation/update-emloyee-evaluation.component';
import { ReviewerEvaluationComponent } from './myComponents/reviewer-evaluation/reviewer-evaluation.component';
import { EmployeeEvaluationComponent } from './myComponents/employee-evaluation/employee-evaluation.component';
import { ReviewerStepComponent } from './myComponents/reviewer-evaluation-step/reviewer-evaluation-step.component';
import { EmployeeCompetenciesComponent } from './myComponents/employee-competencies/employee-competencies.component';
import { EmployeeTeamMatesComponent } from './myComponents/employee-teammates/employee-team-mates.component';
import { ReviewerValidationStepComponent } from './myComponents/reviewer-validation-step/reviewer-validation-step.component';
import { UpdateReviewerEvaluationStepComponent } from './myComponents/update-reviewer-evaluation-step/update-reviewer-evaluation-step.component';
import { DashboardComponent } from './myComponents/dashboard/dashboard.component';
import { UserProfileComponent } from './myComponents/user-profile/user-profile.component';

const APP_CONTAINERS = [
  DefaultFooterComponent,
  DefaultHeaderComponent,
  DefaultLayoutComponent,
];

@NgModule({
  declarations: [AppComponent, ...APP_CONTAINERS,
     UsersComponent,
     LoginComponent,
     RolesComponent,
     NotifyUsersComponent,
     EmployeeStatusComponent,
     EmployeeEvaluationListComponent,
     UpdateEmloyeeEvaluationComponent,
     ReviewerEvaluationComponent,
     EmployeeEvaluationComponent,
     ReviewerStepComponent,
     EmployeeCompetenciesComponent,
     EmployeeTeamMatesComponent,
     ReviewerValidationStepComponent,
     UpdateReviewerEvaluationStepComponent,
     DashboardComponent,
     UserProfileComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    AvatarModule,
    BreadcrumbModule,
    FooterModule,
    DropdownModule,
    GridModule,
    HeaderModule,
    SidebarModule,
    IconModule,
    NavModule,
    ButtonModule,
    FormModule,
    UtilitiesModule,
    ButtonGroupModule,
    ReactiveFormsModule,
    SidebarModule,
    SharedModule,
    TabsModule,
    ListGroupModule,
    ProgressModule,
    BadgeModule,
    ListGroupModule,
    CardModule,
    NgScrollbarModule,
    TableModule,
    ModalModule,
    DataTablesModule,
    FormsModule,
    HttpClientModule,
    AlertModule,
    ToastModule,
    BrowserAnimationsModule,
  	ToastrModule.forRoot(),
    SpinnerModule,
    ColComponent,
    WidgetStatEComponent,
    ChartjsComponent,
    ChartjsComponent,
  ],
  providers: [
    {
      provide: LocationStrategy,
      useClass: HashLocationStrategy
    },
    IconSetService,
    Title
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
