import { INavData } from '@coreui/angular';



export const navItemsLdap: INavData[] = [
  
  {
    name: 'Dashboard',
    url: '/dashboard',
    iconComponent: { name: 'cil-speedometer' },
    permission:['users management','roles management','general informations'],
  },
  // {
  //   name: 'Dashboard',
  //   url: '/users/external-users',
  //   iconComponent: { name: 'cil-speedometer' },
  //   permission:['users management','roles management'],
  // },
  {
    title: true,
    name: 'User(s) management',
    permission:['users management','roles management']
  },
  {
    name: 'External user(s)',
    url: '/users/external-users',
    iconComponent: { name: 'cil-people' },
    permission:['users management']
  },
  {
    title: true,
    name: 'Roles management',
    permission:['roles management']
  },
  {
    name: 'Roles & Permissions',
    url: '/roles',
    iconComponent: { name: 'cil-report-slash' },
    permission:['users management','roles management']
  },

  {
    title: true,
    name: 'General Information(s)',
    permission:['general informations']
  },
  {
    name: 'Competencies',
    url: '/employee-competencies',
    iconComponent: { name: 'cil-puzzle' },
    permission:['general informations']
  },
  {
    name: 'Teammates',
    url: '/employee-teammates',
    iconComponent: { name: 'cil-puzzle' },
    permission:['general informations']
  },

  {
    title: true,
    name: 'Evaluation management',
    permission:['evaluations list','notify employees']
  },
  {
    name: 'Notify user(s)',
    url: '/notify-users',
    iconComponent: { name: 'cil-check' },
    permission:['notify employees']
  },
  {
    name: 'Workfow(s) tracking',
    url: '/employee-status',
    iconComponent: { name: 'cil-notes' },
    permission:['workflow tracking']
  },
  
  {
    name: 'Evaluation(s) List',
    url: '/employee-evaluations-list',
    iconComponent: { name: 'cil-list' },
    permission:['evaluations list']
  },
  {
    name: 'Employees to evaluate',
    url: '/reviewer-evaluation',
    iconComponent: { name: 'cil-task' },
    permission:['reviewer evaluation']
  },
 

]