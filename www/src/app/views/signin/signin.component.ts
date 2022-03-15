import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {AuthService} from "../../services/auth.service";
import {HttpErrorResponse} from "@angular/common/http";
import {User} from "../../models/models";
import {MatDialog} from "@angular/material/dialog";
import {AccesstokenPopupComponent} from "./accesstoken-popup/accesstoken-popup.component";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {

  public readonly usernameControl: FormControl;
  public readonly passwordControl: FormControl;
  public responseMessage: string | null;

  constructor(private readonly auth: AuthService, private readonly dialog: MatDialog) {
    this.responseMessage = null;
    this.usernameControl = new FormControl('', [
      Validators.required
    ]);

    this.passwordControl = new FormControl('', [
      Validators.required
    ]);
  }

  ngOnInit(): void {
  }

  signIn(): void {
    this.auth.authenticate(this.usernameControl.value, this.passwordControl.value).then(value => {
      this.responseMessage = null;
      console.log(value);
      this.auth.loggedInState = true;
      this.auth.setUser(value as User);
      sessionStorage.setItem('user', JSON.stringify({username: value.username, password: this.auth.password}));
    }).catch((error: HttpErrorResponse) => {
      if (error.status === 404) {
        this.responseMessage = 'Der User existiert nicht';
      } else if (error.status == 401) {
        this.responseMessage = 'Passwort ist falsch';
      } else {
        this.responseMessage = "Fehler ist aufgetreten - Versuchen Sie es später erneut";
      }
      console.log(error)
    })
  }

  signInWithAccessToken(): void {
    this.dialog.open(AccesstokenPopupComponent);
  }
}
