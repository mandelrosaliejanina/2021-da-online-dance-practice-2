import {Component, OnInit} from '@angular/core';
import {AuthService} from "./services/auth.service";
import {Observable} from "rxjs";

import {ActivatedRoute, NavigationEnd, Router, UrlTree} from "@angular/router";
import {AccessTokenResponse, User, UserCredential} from "./models/models";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'frontend';
  isLoggedInState: Observable<boolean>;
  user: User | null;
  currentTab: string;
  token: Observable<AccessTokenResponse | null>;

  constructor(private readonly auth: AuthService,
              private readonly router: Router,
              private readonly route: ActivatedRoute) {
    this.isLoggedInState = this.auth.loggedInStateAsObservable;
    this.user = null;
    this.currentTab = "";
    this.token = this.auth.getToken();
  }

  ngOnInit(): void {
    const credentials: UserCredential = JSON.parse(sessionStorage.getItem('user') || 'null') as UserCredential;
    if (credentials) {
      this.auth.authenticate(credentials.username, credentials.password).then(value => {
        this.auth.loggedInState = true;
        this.auth.setUser(value as User);
      })
    }
    this.auth.loggedInStateAsObservable.subscribe(state => {
      let redirectUrl: string | UrlTree = '/home';


      if (state) {
        this.route.queryParams.subscribe(value => {
          if (value["redirectUrl"]) {
            redirectUrl = value["redirectUrl"];
          }
        });
      } else {
        this.route.queryParams.subscribe(value => {
          if (value["redirectUrl"]) {
            redirectUrl = this.router.createUrlTree(
              ['/signin'], {
                queryParams: {
                  redirectUrl: value["redirectUrl"]
                }
              }
            );
          } else {
            redirectUrl = '/signin';
          }
        });
      }

      this.router.navigateByUrl(redirectUrl, {replaceUrl: true})
        .catch(err => {
          console.error(err);
        });
    });

    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.currentTab = event.url;
      }
    })

    this.auth.userObservable.subscribe(user => {
      this.user = user
    })
  }

  logout() {
    this.auth.signOut();
  }
}
