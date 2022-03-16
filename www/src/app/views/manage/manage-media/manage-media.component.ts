import {Component, OnInit} from '@angular/core';
import {environment} from '../../../../environments/environment';
import {FormBuilder, FormGroup} from '@angular/forms';
import {BackendService} from '../../../services/backend.service';
import {AccessToken, DFile, Usage} from "../../../models/models";
import {MatDialog} from "@angular/material/dialog";
import {Clipboard} from "@angular/cdk/clipboard";
import {DetailedMediaComponent} from "./detailed-media/detailed-media.component";

@Component({
  selector: 'app-manage-media',
  templateUrl: './manage-media.component.html',
  styleUrls: ['./manage-media.component.scss']
})
export class ManageMediaComponent implements OnInit {
  contents: DFile[] | null;
  usage: Usage | null;

  public readonly baseUrl: string;
  uploadForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private readonly backendservice: BackendService,
              private readonly dialog: MatDialog,
              private readonly clipboard: Clipboard) {
    this.baseUrl = environment.baseUrl
    this.uploadForm = this.formBuilder.group({
      file: ['']
    })
    this.contents = null;
    this.usage = null;
  }

  ngOnInit(): void {
    this.backendservice.get('file/findall').then(value => {
      this.contents = value as DFile[];
    });
  }

  submit() {
    const formData = new FormData();
    formData.append("file", this.uploadForm.get("file")?.value);
    this.backendservice.post("file", formData).then(value => {
      this.contents?.push(value as DFile);
    });
    alert("file is uploaded")
  }

  delete(content: DFile) {
    this.backendservice.delete(`file/${content.id}`).then(() => {
      this.contents = this.contents!.filter(a => a.id !== content.id);
    })
  }

  onFileSelect(event: any) {
    if (event.target.files.length > 0) {
      this.uploadForm.get("file")?.setValue(event.target.files[0]);
    }
  }

  create() {
    this.dialog.open(DetailedMediaComponent).afterClosed().subscribe(value => {
      if (value) {
        this.contents?.push(value as DFile);
        //this.backendservice.post('usage', this.buildUsageObjectForServer(value as Usage));
      }
    });
  }

  private buildUsageObjectForServer(usage: Usage) {
    return {
      course: usage.course,
      file: usage.file,
    };
  }
}
