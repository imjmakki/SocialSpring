import { Component, OnInit } from '@angular/core';
import {FacebookLoginProvider, GoogleLoginProvider, SocialAuthService, SocialUser} from "angularx-social-login";

@Component({
  selector: 'app-social',
  templateUrl: './social.component.html',
  styleUrls: ['./social.component.css']
})
export class SocialComponent implements OnInit {

  user: SocialUser = new SocialUser;
  isLogin: boolean = false;
  constructor(private authService: SocialAuthService,
              private social: SocialAuthService) { }

  ngOnInit(): void {
    this.authService.authState.subscribe(
      data => {
        this.isLogin = (data != null);
      }
    );
  }

  getTokenGoogle(){
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID).then(
      data => {
        return data.idToken;
      }
    );
  }

  signInWithGoogle(): void {
    this.getTokenGoogle();
  }

  signInWithFB(): void {
    this.authService.signIn(FacebookLoginProvider.PROVIDER_ID).then(
      data => {
        this.user = data;
        console.log(data);
      }
    );
  }

  signOut(): void {
    this.authService.signOut();
  }
}
