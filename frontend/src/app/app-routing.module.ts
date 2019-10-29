import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CelebritiesComponent } from './celebrities/celebrities.component';
import { HomeComponent } from './home/home.component';
import { DataScienceComponent } from './data-science/data-science.component';
import { AppAuthGuard } from './services/auth.service';
import { ROLES } from './models/models';


const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [ROLES.SUPERVISOR, ROLES.USER, ROLES.DATA]
     }
   },
  {  path: 'celebrities',
    component: CelebritiesComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [ROLES.SUPERVISOR , ROLES.USER]
     }
   },
  {  path: 'data',
    component: DataScienceComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [ROLES.DATA]
     }
   },
  {  path: '**', component: HomeComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
 })
export class AppRoutingModule {   }
