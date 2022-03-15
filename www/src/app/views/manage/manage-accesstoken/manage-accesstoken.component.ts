import {Component, OnInit} from '@angular/core';
import {BackendService} from "../../../services/backend.service";
import {AccessToken, Course} from "../../../models/models";
import {MatDialog} from "@angular/material/dialog";
import {DetailedAccesstokenComponent} from "./detailed-accesstoken/detailed-accesstoken.component";
import {Clipboard} from "@angular/cdk/clipboard";
import {MAT_TOOLTIP_DEFAULT_OPTIONS, MatTooltip} from "@angular/material/tooltip";

@Component({
  selector: 'app-manage-accesstoken',
  templateUrl: './manage-accesstoken.component.html',
  styleUrls: ['./manage-accesstoken.component.scss'],
  providers: [{provide: MAT_TOOLTIP_DEFAULT_OPTIONS, useValue: {hideDelay: 2000}}]
})
export class ManageAccesstokenComponent implements OnInit {

  accessTokens: AccessToken[] | null;
  courses: Course[] | null;

  constructor(private readonly backend: BackendService,
              private readonly dialog: MatDialog,
              private readonly clipboard: Clipboard
  ) {
    this.accessTokens = null;
    this.courses = null;
  }

  ngOnInit(): void {
    this.backend.get('accessToken').then(value => {
      this.accessTokens = value as AccessToken[];
    });
  }

  edit(accessToken: AccessToken): void {
    this.dialog.open(DetailedAccesstokenComponent, {
      data: {...accessToken}
    }).afterClosed().subscribe(value => {
      if (value) {
        this.backend.put('accessToken', this.buildAccessTokenObjectForServer(value as AccessToken))
          .then(value => {
            const token = value as AccessToken;
            const index = this.accessTokens!.findIndex(a => a.token === token.token);
            this.accessTokens![index] = token;
          });
      }
    });
  }

  create(): void {
    this.dialog.open(DetailedAccesstokenComponent).afterClosed().subscribe(value => {
      if (value) {
        this.backend.post('accessToken', this.buildAccessTokenObjectForServer(value as AccessToken))
          .then(value => {
            this.accessTokens?.push(value as AccessToken);
          });
      }
    });
  }

  delete(accessToken: AccessToken) {
    this.backend.delete(`accessToken/${accessToken.token}`).then(() => {
      this.accessTokens = this.accessTokens!.filter(a => a.token !== accessToken.token);
    })
  }

  copyToken(token: string, tooltip: MatTooltip): void {
    this.clipboard.copy(token);
    tooltip.show();
    setTimeout(() => tooltip.hide(), 1500);
  }

  private formatDate(date: Date): string {
    return `${date.getFullYear()}-${('0' + (date.getMonth() + 1)).slice(-2)}-${('0' + date.getDate()).slice(-2)}`;
  }

  private buildAccessTokenObjectForServer(accessToken: AccessToken) {
    return {
      token: accessToken.token,
      course: accessToken.course,
      activationDate: accessToken.activationDate ? this.formatDate(new Date(accessToken.activationDate)) : null,
      daysValid: accessToken.daysValid,
      expireDate: accessToken.expireDate ? this.formatDate(new Date(accessToken.expireDate)) : null,
    };
  }
}
