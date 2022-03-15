import {Component, OnInit} from '@angular/core';
import {AccessTokenResponse, DFile} from "../../../models/models";
import {BackendService} from "../../../services/backend.service";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-accesstoken-home',
  templateUrl: './accesstoken-home.component.html',
  styleUrls: ['./accesstoken-home.component.scss']
})
export class AccesstokenHomeComponent implements OnInit {
  selectedCourse: string | null;
  files: DFile[] | null;
  token: AccessTokenResponse | null;

  constructor(private readonly backend: BackendService,
              private readonly auth: AuthService) {
    this.files = null;
    this.selectedCourse = this.auth.token?.courseName || null;
    this.token = this.auth.token;


    this.backend.get<DFile[]>(`course/filesByCourse/${this.token?.courseId}/token`).then(files => {
      this.files = files;
    });
  }

  ngOnInit(): void {

  }

  getVideoFiles(files: DFile[] | null): DFile[] {
    return files?.filter(f => f.contentType === 'VIDEO') || [];
  }

  getAudioFiles(files: DFile[] | null): DFile[] {
    return files?.filter(f => f.contentType === 'AUDIO') || [];
  }
}
