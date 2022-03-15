import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {BackendService} from "../../../services/backend.service";
import {Router} from "@angular/router";
import {AccessTokenResponse} from "../../../models/models";
import {AuthService} from "../../../services/auth.service";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-accesstoken-popup',
  templateUrl: './accesstoken-popup.component.html',
  styleUrls: ['./accesstoken-popup.component.scss']
})
export class AccesstokenPopupComponent implements OnInit {
  accesstokenControl: FormControl;
  responseMessage: string | null;

  constructor(private readonly backend: BackendService,
              private readonly router: Router,
              private readonly auth: AuthService,
              private readonly dialogRef: MatDialogRef<AccesstokenPopupComponent>) {
    this.responseMessage = null;
    this.accesstokenControl = new FormControl('', [
      Validators.required
    ]);
  }

  ngOnInit(): void {
  }

  signIn(): void {
    if (this.accesstokenControl.valid) {
      this.backend.post<AccessTokenResponse>("accessToken/validate", {
        token: this.accesstokenControl.value
      }).then(token => {
          if (token.isValid) {
            this.auth.setToken(token);
            this.dialogRef.close();
            this.router.navigateByUrl(`accesstoken-home`);
          }
        }
      );
    }
  }
}
