import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './layout/header/header.component';
import {RouterModule, Routes} from "@angular/router";
import { SocialComponent } from './layout/social/social.component';
import { HomeComponent } from './layout/home/home.component';
import { ProfileComponent } from './layout/profile/profile.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: '/login', component: SocialComponent},
  {path: '/home', component: HomeComponent},
  {path: '/profile', component: ProfileComponent},
  {path: '**', redirectTo: '/home' }
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SocialComponent,
    HomeComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
