import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ManageComponent} from './manage.component';
import {ManageMediaComponent} from './manage-media/manage-media.component';
import {RouterModule, Routes} from '@angular/router';
import {MatMenuModule} from '@angular/material/menu';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {ManageCourseComponent} from './manage-course/manage-course.component';
import {ManageAccesstokenComponent} from './manage-accesstoken/manage-accesstoken.component';
import {MatSelectModule} from "@angular/material/select";
import {MatInputModule} from "@angular/material/input";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {MatIconModule} from "@angular/material/icon";
import {DetailedAccesstokenComponent} from './manage-accesstoken/detailed-accesstoken/detailed-accesstoken.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatTooltipModule} from "@angular/material/tooltip";
import {DetailedCourseComponent} from './manage-course/detailed-course/detailed-course.component';
import { DetailedMediaComponent } from './manage-media/detailed-media/detailed-media.component';

const routes: Routes = [
  {
    path: '',
    component: ManageComponent
  },
  {
    path: 'manage-media',
    component: ManageMediaComponent
  },
  {
    path: 'manage-course',
    component: ManageCourseComponent
  },
  {
    path: 'manage-accesstoken',
    component: ManageAccesstokenComponent
  }
]

@NgModule({
  declarations: [
    ManageComponent,
    ManageMediaComponent,
    ManageCourseComponent,
    ManageAccesstokenComponent,
    DetailedAccesstokenComponent,
    DetailedCourseComponent,
    DetailedMediaComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatMenuModule,
    FormsModule,
    MatButtonModule,
    MatSelectModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatIconModule,
    MatDialogModule,
    MatTooltipModule,
    ReactiveFormsModule
  ]
})
export class ManageModule { }
