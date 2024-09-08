import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ClienteleComponent} from "./component/clientele/clientele.component";
import {authGuard} from "./guard/auth.guard";
import {loginGuard} from "./guard/login.guard";
import {LoginComponent} from "./component/login/login.component";
import {LogoutComponent} from "./component/logout/logout.component";
import {PatientComponent} from "./component/patient/patient.component";
import {NoteComponent} from "./component/note/note.component";

const routes: Routes = [

  {path: '', redirectTo: '/login', pathMatch: 'full'},

  {path: 'login', component: LoginComponent, canActivate: [loginGuard], runGuardsAndResolvers: 'always'},


  {
    path: 'clientele',
    component: ClienteleComponent,
    canActivate: [authGuard],
    canActivateChild: [authGuard],
    runGuardsAndResolvers: 'always'
  },

  {path: 'logout', component: LogoutComponent},

  {path: 'patient/:patientId', component: PatientComponent},

  {path: 'note/:patientId', component: NoteComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
