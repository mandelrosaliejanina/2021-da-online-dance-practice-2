import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {AccesstokenHomeComponent} from "./accesstoken-home.component";
import {AccessAudioComponent} from './access-audio/access-audio.component';
import {AccessVideoComponent} from './access-video/access-video.component';
import {MatIconModule} from "@angular/material/icon";


const routes: Routes = [
  {
    path: '',
    component: AccesstokenHomeComponent
  }
]

@NgModule({
  declarations: [
    AccesstokenHomeComponent,
    AccessAudioComponent,
    AccessVideoComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatIconModule,
  ]
})
export class AccesstokenHomeModule {
}
