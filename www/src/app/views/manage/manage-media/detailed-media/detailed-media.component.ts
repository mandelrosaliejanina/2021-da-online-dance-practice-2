import {Component, Inject, OnInit} from '@angular/core';
import {Course, Usage} from "../../../../models/models";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BackendService} from "../../../../services/backend.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-detailed-media',
  templateUrl: './detailed-media.component.html',
  styleUrls: ['./detailed-media.component.scss']
})
export class DetailedMediaComponent implements OnInit {
  usage : Usage | null;
  courses : Course [] |null;
  uploadForm: FormGroup;
  selectedCourse: Course | null;



  constructor(private readonly backend: BackendService,
              private formBuilder: FormBuilder,
              private readonly dialogRef: MatDialogRef<DetailedMediaComponent>,
              @Inject(MAT_DIALOG_DATA) private readonly data: Usage,
              private _snackBar: MatSnackBar) {
    this.usage = data || {};
    this.courses = null;
    this.uploadForm = this.formBuilder.group({
      file: [''],
      description: ['',Validators.required]
    })
    this.selectedCourse = null;
  }

  ngOnInit(): void {
    this.backend.get('course').then(value => {
      this.courses = value as Course[];
      this.selectedCourse = this.courses[0];
    });
  }

  async submit() {
    const formData = new FormData();
    formData.append("file", this.uploadForm.get("file")?.value);

    if(this.uploadForm.get("file")){
      const imagename = this.uploadForm.get("file")?.value.name

      let file = this.uploadForm.get("file")?.value;

      //überprüfen ob file zu groß
      if(file.size > 1024000000){
        alert("Datei is zu groß!")
      }

      const blobToUpload = await this.fileToBlob(file);
      this.backend.postFile('', blobToUpload, imagename,this.selectedCourse!,this.uploadForm.get("description")?.value).then(() => {
        this.openSnackBar("Datei hochladen war erfolgreich!")
        this.close()
      })
    }
  }

  close(): void{
    this.dialogRef.close();
  }

  openSnackBar(message: string):void {
    this._snackBar.open(message,"",{duration: 2500});
  }

///
  async fileToBlob(file:File){
    let blobParts = new Uint8Array(await file.arrayBuffer());
    return new Blob([blobParts], {type: file.type })
  }


  onFileSelect(event: any) {
    if (event.target.files.length > 0) {
      this.uploadForm.get("file")?.setValue(event.target.files[0]);
    }
  }


}
