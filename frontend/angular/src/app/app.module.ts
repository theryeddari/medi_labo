import {NgModule} from '@angular/core';
import {BrowserModule, provideClientHydration} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, provideHttpClient, withFetch} from "@angular/common/http";
import {ClienteleComponent} from './component/clientele/clientele.component';
import {AuthInterceptor} from "./interceptor/auth-interceptor.service";
import {LogoutComponent} from "./component/logout/logout.component";
import {FormsModule} from "@angular/forms";
import {LoginComponent} from "./component/login/login.component";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ClienteleComponent,
    LogoutComponent
  ],
  imports: [
    BrowserModule,
    FormsModule, //bidirection formulaire enfant parent et inversement
    AppRoutingModule
  ],
  providers: [
    provideClientHydration(),
    provideHttpClient(withFetch()),
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
