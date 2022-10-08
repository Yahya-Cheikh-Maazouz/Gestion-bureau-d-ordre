import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ExpediteurComponent} from "./components/features/expediteur/expediteur.component";
import {DashboardComponent} from "./components/features/dashboard/dashboard.component";
import {CourriersComponent} from "./components/features/courrier/courriers/courriers.component";
import {MyaccountComponent} from "./components/features/settings/myaccount/myaccount.component";
import {HomeComponent} from "./components/pages/home/home.component";
import {LoginComponent} from "./components/features/authentication/login/login.component";
import {MainPageComponent} from "./components/pages/main-page/main-page.component";
import {AuthGuard} from "./core/guards/auth.guard";
import {AdminGuard} from "./core/guards/admin.guard";
import {UserGuard} from "./core/guards/user.guard";
import {AccountsComponent} from "./components/features/settings/accounts/accounts.component";
import {DocumentationComponent} from "./components/features/documentation/documentation.component";
import {ContactComponent} from "./components/features/contact/contact.component";
import {SignupComponent} from "./components/features/authentication/signup/signup.component";

const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  { path: 'login',component: LoginComponent },
  { path: 'register',component: SignupComponent },
  { path: 'user/:id',component: MainPageComponent,
    children:[
      {path: '', redirectTo: 'home', pathMatch: 'full'},
      { path: 'home', component: CourriersComponent },
      { path: 'courriers', component: CourriersComponent },
      { path: 'expediteur', component: ExpediteurComponent,canActivate:[AdminGuard] },
      { path: 'account', component: MyaccountComponent },
      { path: 'doc', component: DocumentationComponent },
      { path: 'contact', component: ContactComponent },
      { path: 'accounts', component: AccountsComponent,canActivate:[AdminGuard] },
      { path: 'dashborad', component: DashboardComponent ,canActivate:[AdminGuard]}
    ], canActivate:[AuthGuard],canActivateChild:[UserGuard]
  },

/*  { path: '**', redirectTo: 'page-not-found' }*/

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
