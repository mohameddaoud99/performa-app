import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DefaultLayoutComponent } from './containers';
import { Page404Component } from './views/pages/page404/page404.component';
import { Page500Component } from './views/pages/page500/page500.component';
//import { LoginComponent } from './views/pages/login/login.component';
import { RegisterComponent } from './views/pages/register/register.component';
import { UsersComponent } from './myComponents/users/users.component';
import { LoginComponent } from './myComponents/login/login.component';
import { AuthGuard } from './guards/auth.guard';
import { NotauthGuard } from './guards/noauth.guard';
import { RolesComponent } from './myComponents/roles/roles.component';
import { NotifyUsersComponent } from './myComponents/notify-users/notify-users.component';
import { EmployeeStatusComponent } from './myComponents/employee-status/employee-status.component';
import { EmployeeEvaluationListComponent } from './myComponents/employee-evaluation-list/employee-evaluation-list.component';
import { UpdateEmloyeeEvaluationComponent } from './myComponents/update-emloyee-evaluation/update-emloyee-evaluation.component';
import { ReviewerEvaluationComponent } from './myComponents/reviewer-evaluation/reviewer-evaluation.component';
import { UserCompetenciesComponent } from './myComponents/user-competencies/user-competencies.component';
import { EmployeeEvaluationComponent } from './myComponents/employee-evaluation/employee-evaluation.component';
import { ReviewerStepComponent } from './myComponents/reviewer-evaluation-step/reviewer-evaluation-step.component';
import { EmployeeCompetenciesComponent } from './myComponents/employee-competencies/employee-competencies.component';
import { EmployeeTeamMatesComponent } from './myComponents/employee-teammates/employee-team-mates.component';
import { ReviewerValidationStepComponent } from './myComponents/reviewer-validation-step/reviewer-validation-step.component';
import { UpdateReviewerEvaluationStepComponent } from './myComponents/update-reviewer-evaluation-step/update-reviewer-evaluation-step.component';
import { DashboardComponent } from './myComponents/dashboard/dashboard.component';
import { UserProfileComponent } from './myComponents/user-profile/user-profile.component';




const routes: Routes = [
  // {
  //   path: '',
  //   redirectTo: 'login',
  //   pathMatch: 'full',
  //   //canActivate:[NotauthGuard]
  // },
  {
    path: "login", component: LoginComponent,canActivate:[NotauthGuard]
  },
  {
    path: "", component: LoginComponent,canActivate:[NotauthGuard]
  },
  {
    path: "*", component: LoginComponent,canActivate:[NotauthGuard]
  },
  {
    path: '',
    component: DefaultLayoutComponent,
    data: {
      title: 'Home'
    },
    children: [
      // {
      //   path: 'dashboard',
      //   canActivate: [AuthGuard],
      //   loadChildren: () =>
      //     import('./views/dashboard/dashboard.module').then((m) => m.DashboardModule) 
      // },
      {
        path: 'dashboard',
        component: DashboardComponent,
        canActivate: [AuthGuard],
        data: {
          title: 'Dashboard'
        }
      },

     
      {
        path: 'users/external-users',
        component: UsersComponent,
        canActivate: [AuthGuard],
        data: {
          title: 'Users management'
        }
      },
      {
        path: 'user-profile',
        component: UserProfileComponent,
        canActivate: [AuthGuard],
        data: {
          title: 'User profile'
        }
      },
      {
        path: 'roles',
        component: RolesComponent,
        canActivate: [AuthGuard],
        data: {
          title: 'Roles management'
        }
        
      },
      {
        path: 'notify-users',
        component: NotifyUsersComponent,
        canActivate: [AuthGuard],
        data: {
          title: 'Notify users'
        }
        
      },
      {
        path: 'employee-status',
        component: EmployeeStatusComponent,
        canActivate: [AuthGuard],
        data: {
          title: 'Workfow(s) tracking'
        }
      },
      {
        path: 'employee-evaluation',
        component: EmployeeEvaluationComponent,
        canActivate: [AuthGuard],
        data: {
          title: 'Employee Evaluation'
        }
      },
      {
        path: 'employee-evaluations-list',
        component: EmployeeEvaluationListComponent,
        canActivate: [AuthGuard],
        data: {
          title: 'Employee Evaluation(s) List'
        }
      },
      {
        path: 'evaluation/:id',
        component: UpdateEmloyeeEvaluationComponent,
        canActivate: [AuthGuard],
        data: {
          title: 'Update Employee Evaluation'
        }
      },
      {
        path: 'reviewer-step-update-evaluation/:id',
        component: UpdateReviewerEvaluationStepComponent,
        canActivate: [AuthGuard],
        data: {
          title: 'Update Evaluation(Reviwer Step)'
        }
      },
      {
        path: 'reviewer-evaluation',
        component: ReviewerEvaluationComponent,
        canActivate: [AuthGuard],
        data: {
          title: 'Reviewer Evaluation'
        }
      },
      {
        path: 'reviewer-validation-step/:id',
        component: ReviewerValidationStepComponent,
        canActivate: [AuthGuard],
        data: {
          title: 'Reviewer Validation Step '
        }
      },
      {
        path: 'reviewer-evaluation-step/:id',
        component: ReviewerStepComponent,
        canActivate: [AuthGuard],
        data: {
          title: 'Reviewer Evaluation Step'
        }
      },
      {
        path: 'employee-competencies',
        component: EmployeeCompetenciesComponent,
        canActivate: [AuthGuard],
        data: {
          title: 'Employee competencies'
        }
      },
      {
        path: 'employee-teammates',
        component: EmployeeTeamMatesComponent,
        canActivate: [AuthGuard],
        data: {
          title: 'Teammates'
        }
      },







      {
        path: 'theme',
        loadChildren: () =>
          import('./views/theme/theme.module').then((m) => m.ThemeModule)
      },
      {
        path: 'base',
        loadChildren: () =>
          import('./views/base/base.module').then((m) => m.BaseModule)
      },
      {
        path: 'buttons',
        loadChildren: () =>
          import('./views/buttons/buttons.module').then((m) => m.ButtonsModule)
      },
      {
        path: 'forms',
        loadChildren: () =>
          import('./views/forms/forms.module').then((m) => m.CoreUIFormsModule)
      },
      {
        path: 'charts',
        loadChildren: () =>
          import('./views/charts/charts.module').then((m) => m.ChartsModule)
      },
      {
        path: 'icons',
        loadChildren: () =>
          import('./views/icons/icons.module').then((m) => m.IconsModule)
      },
      {
        path: 'notifications',
        loadChildren: () =>
          import('./views/notifications/notifications.module').then((m) => m.NotificationsModule)
      },
      {
        path: 'widgets',
        loadChildren: () =>
          import('./views/widgets/widgets.module').then((m) => m.WidgetsModule)
      },
      {
        path: 'pages',
        loadChildren: () =>
          import('./views/pages/pages.module').then((m) => m.PagesModule)
      },
    ]
  },

  {
    path: 'login',
    component: LoginComponent,
    data: {
      title: 'Login Page'
    }
    
  },
  {
    path: '404',
    component: Page404Component,
    data: {
      title: 'Page 404'
    }
  },
  {
    path: '500',
    component: Page500Component,
    data: {
      title: 'Page 500'
    }
  },
  /*{
    path: 'loginn',
    component: LoginComponent,
    data: {
      title: 'Login Page'
    }
  },*/
  {
    path: 'register',
    component: RegisterComponent,
    data: {
      title: 'Register Page'
    }
  },
  {path: '**', redirectTo: 'dashboard'}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      scrollPositionRestoration: 'top',
      anchorScrolling: 'enabled',
      initialNavigation: 'enabledBlocking'
      // relativeLinkResolution: 'legacy'
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
