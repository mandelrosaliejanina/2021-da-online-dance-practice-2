import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HomeComponent} from './home.component';
import {RouterModule, Routes} from "@angular/router";
import {MatButtonModule} from "@angular/material/button";
import {MatListModule} from "@angular/material/list";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatSliderModule} from "@angular/material/slider";
import {MatCardModule} from "@angular/material/card";
import {MatIconModule} from "@angular/material/icon";
import {FormsModule} from "@angular/forms";
import {FlexModule} from "@angular/flex-layout";
import {MatMenuModule} from '@angular/material/menu';
import {MediaContentComponent} from './media-content/media-content.component';
import {VideoComponent} from './media-content/video/video.component';
import {AudioComponent} from './media-content/audio/audio.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  }
]

@NgModule({
  declarations: [
    HomeComponent,
    MediaContentComponent,
    VideoComponent,
    AudioComponent
  ],
  exports: [
    MediaContentComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatButtonModule,
    MatListModule,
    MatToolbarModule,
    MatSliderModule,
    MatCardModule,
    MatIconModule,
    FormsModule,
    FlexModule,
    MatMenuModule
  ]
})
export class HomeModule {
}
