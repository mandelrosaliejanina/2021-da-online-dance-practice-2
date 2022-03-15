import {Component, Inject, OnInit} from '@angular/core';
import {AccessToken, Course} from "../../../../models/models";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {BackendService} from "../../../../services/backend.service";
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-detailed-accesstoken',
  templateUrl: './detailed-accesstoken.component.html',
  styleUrls: ['./detailed-accesstoken.component.scss']
})
export class DetailedAccesstokenComponent implements OnInit {

  accessToken: AccessToken | null;
  courses: Course[] | null;
  courseControl: FormControl;


  constructor(private readonly backend: BackendService,
              private readonly dialogRef: MatDialogRef<DetailedAccesstokenComponent>,
              @Inject(MAT_DIALOG_DATA) private readonly data: AccessToken) {
    this.accessToken = data || {};
    this.courses = null;
    this.courseControl = new FormControl('', [
      Validators.required
    ])
  }

  ngOnInit(): void {
    this.backend.get('course').then(value => {
      this.courses = value as Course[];
      if (this.accessToken?.course) {
        this.accessToken.course = this.courses.find(c => c.id === this.accessToken?.course.id)!;
      } else {
        this.accessToken!.course = this.courses[0];
      }
    });
  }

  cancel(): void {
    this.dialogRef.close(null);
  }

  save(): void {
    this.dialogRef.close(this.accessToken);
  }
}
