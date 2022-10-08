import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SideBarComponent } from './components/pages/side-bar/side-bar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from "@angular/material/icon";
import { NavbarComponent } from './components/pages/navbar/navbar.component';
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTableModule} from "@angular/material/table";
import { SaveCourrierModalComponent } from './components/shared/modal/save-courrier-modal/save-courrier-modal.component';
import { UpdateCourrierModalComponent } from './components/shared/modal/update-courrier-modal/update-courrier-modal.component';
import { ExpediteurComponent } from './components/features/expediteur/expediteur.component';
import { SaveExpediteurModalComponent } from './components/shared/modal/save-expediteur-modal/save-expediteur-modal.component';
import { UpdateExpediteurModalComponent } from './components/shared/modal/update-expediteur-modal/update-expediteur-modal.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { OpenPopupComponent } from './components/shared/modal/open-popup/open-popup.component';
import { MatButtonModule } from '@angular/material/button';
import { DashboardComponent } from './components/features/dashboard/dashboard.component';
import { CourriersComponent } from './components/features/courrier/courriers/courriers.component';
import {MatCheckboxModule} from "@angular/material/checkbox";
import { MyaccountComponent } from './components/features/settings/myaccount/myaccount.component';
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {MatSidenavModule} from "@angular/material/sidenav";
import { HomeComponent } from './components/pages/home/home.component';
import {LoginComponent} from "./components/features/authentication/login/login.component";
import {SignupComponent} from "./components/features/authentication/signup/signup.component";
import {StoreModule} from "@ngrx/store";
import {_authReducer} from "./core/store/auth/auth.reducers";
import {EffectsModule} from "@ngrx/effects";
import {AuthenticationEffects} from "./core/store/auth/auth.effects";
import {StoreDevtoolsModule} from "@ngrx/store-devtools";
import {environment} from "../environments/environment";
import { MainPageComponent } from './components/pages/main-page/main-page.component';
import {AuthGuard} from "./core/guards/auth.guard";
import {AdminGuard} from "./core/guards/admin.guard";
import {UserGuard} from "./core/guards/user.guard";
import { SafePipe } from './core/pipes/safe.pipe';
import { CourrierstatePipe } from './core/pipes/courrierstate.pipe';
import { AccountsComponent } from './components/features/settings/accounts/accounts.component';
import { DocumentationComponent } from './components/features/documentation/documentation.component';
import { ContactComponent } from './components/features/contact/contact.component';
import { AnimMainPageComponent } from './components/features/animations/anim-main-page/anim-main-page.component';
import {JwtInterceptor} from "./core/interceptors/jwt.interceptor";
import { SaveUserComponent } from './components/shared/modal/save-user/save-user.component';

@NgModule({
  declarations: [
    AppComponent,
    SideBarComponent,
    NavbarComponent,
    SaveCourrierModalComponent,
    UpdateCourrierModalComponent,
    ExpediteurComponent,
    SaveExpediteurModalComponent,
    UpdateExpediteurModalComponent,
    OpenPopupComponent,
    DashboardComponent,
    CourriersComponent,
    MyaccountComponent,
    HomeComponent,
    LoginComponent,SignupComponent, MainPageComponent, SafePipe, CourrierstatePipe, AccountsComponent, DocumentationComponent, ContactComponent, AnimMainPageComponent, SaveUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatPaginatorModule,
    MatTableModule, HttpClientModule, ReactiveFormsModule, MatButtonModule, FormsModule, MatCheckboxModule, MatOptionModule, MatSelectModule, MatSidenavModule
    ,StoreModule.forRoot( {authReducer: _authReducer}),
    EffectsModule.forRoot([AuthenticationEffects]),StoreDevtoolsModule.instrument({
      maxAge: 25, // Retains last 25 states
      logOnly: environment.production, // Restrict extension to log-only mode
    })
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },AuthGuard,AdminGuard,UserGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
