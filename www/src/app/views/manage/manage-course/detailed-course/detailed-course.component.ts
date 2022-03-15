import {Component, Inject, OnInit} from '@angular/core';
import {Course, Level} from '../../../../models/models';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {BackendService} from '../../../../services/backend.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-detailed-course',
  templateUrl: './detailed-course.component.html',
  styleUrls: ['./detailed-course.component.scss']
})
export class DetailedCourseComponent implements OnInit {

  selectedLevel: Level| null;
  levels: Level[] | null;
  formGroup: FormGroup;

  constructor(private readonly backend: BackendService,
              private readonly dialogRef: MatDialogRef<DetailedCourseComponent>,
              @Inject(MAT_DIALOG_DATA) private readonly data: Course) {

    this.levels = null;
    this.selectedLevel = null;
    this.formGroup = new FormGroup( {
      title: new FormControl( this.data?.title || "",[Validators.required]),
      description: new FormControl(this.data?.description || "",[Validators.required])
    })
  }

  ngOnInit(): void {
    this.backend.get('level').then(value => {
      this.levels = value as Level[];
      this.selectedLevel = this.data?.level || this.levels[0];
      if (this.data?.level) {
        this.selectedLevel = this.levels.find(l => l.id === this.data?.level.id) || this.levels[0]
      }
    })
  }

  cancel(): void {
    this.dialogRef.close(null);
  }

  save(): void {
    const course: Course= {
      title: this.formGroup.get('title')?.value,
      description: this.formGroup.get('description')?.value,
      level: this.selectedLevel!,
      id: this.data?.id
    }
    this.dialogRef.close(course);
  }
}
