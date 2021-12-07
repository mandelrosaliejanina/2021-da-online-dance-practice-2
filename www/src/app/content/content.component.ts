import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ContentService, DFile} from '../content.service';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {
  title = 'frontend';
  videoSource = '';
  audioSource = '';
  audio: any;
  files: DFile[];
  fileName = '';
  cards: ImyCard[];
  value: string | null = null;
  @Input() contentSelected!: string | null;
  @Input() contentSelected02!: string | null;
  @Output() contSelected: EventEmitter<string> = new EventEmitter<string>();
  gridColumns = 4;


  constructor(private http: HttpClient, public contentService: ContentService) {
    this.files = [];

    contentService.getPath(32).subscribe(path => {
      console.log(path);
      this.videoSource = path;
    });

    contentService.getFiles().subscribe(files => {
      this.files = files;
    });

    this.cards = [{
      title: 'abc',
      text: 'cde'
    }];
  }

  toggleGridColumns(): any {
    this.gridColumns = this.gridColumns === 4 ? 5 : 4;
  }

  onFileSelected(event: any): void {

    const file: File = event.target.files[0];

    if (file) {
      this.fileName = file.name;
      const formData = new FormData();
      formData.append('thumbnail', file);
      const upload$ = this.http.post('http://localhost:8080/api/file/upload', formData);
      upload$.subscribe();
    }
  }

  // tslint:disable-next-line:typedef
  content(s: string) {
    // @ts-ignore
    document.getElementById('lname').value = s;
    this.contSelected.emit(s);
  }

  ngOnInit(): void {
  }

}

export interface ImyCard{
  title: string;
  text: string;
}
