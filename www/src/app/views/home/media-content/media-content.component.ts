import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Course, DFile} from "../../../models/models";
import {BackendService} from "../../../services/backend.service";

@Component({
  selector: 'app-media-content',
  templateUrl: './media-content.component.html',
  styleUrls: ['./media-content.component.scss']
})
export class MediaContentComponent implements OnInit, OnChanges {

  @Input()
  course!: Course;
  files: DFile[] | null;


  constructor(private readonly backend: BackendService) {
    this.files = null;
  }

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes["course"]) {
      this.backend.get<DFile[]>(`course/filesByCourse/${this.course.id}/user`).then(files => {
        this.files = files;
      });
    }
  }

  getVideoFiles(files: DFile[] | null): DFile[] {
    return files?.filter(f => f.contentType === 'VIDEO') || [];
  }

  getAudioFiles(files: DFile[] | null): DFile[] {
    return files?.filter(f => f.contentType === 'AUDIO') || [];
  }
}
