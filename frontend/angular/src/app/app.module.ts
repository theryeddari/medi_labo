import {NgModule} from '@angular/core';
import {BrowserModule, provideClientHydration} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {provideHttpClient, withFetch} from "@angular/common/http";
import {ClienteleComponent} from './component/clientele/clientele.component';
import {LogoutComponent} from "./component/logout/logout.component";
import {FormsModule} from "@angular/forms";
import {LoginComponent} from "./component/login/login.component";
import {PatientComponent} from './component/patient/patient.component';
import {NoteComponent} from './component/note/note.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ClienteleComponent,
    LogoutComponent,
    PatientComponent,
    NoteComponent
  ],
  imports: [
    BrowserModule,
    FormsModule, //bidirection formulaire enfant parent et inversement
    AppRoutingModule,
  ],
  providers: [
    provideClientHydration(),
    provideHttpClient(withFetch()),
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
