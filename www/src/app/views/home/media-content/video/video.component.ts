import {Component, Input, OnInit} from '@angular/core';
import {DFile} from "../../../../models/models";
import {environment} from "../../../../../environments/environment";

@Component({
  selector: 'app-video',
  templateUrl: './video.component.html',
  styleUrls: ['./video.component.scss']
})
export class VideoComponent implements OnInit {

  @Input()
  file!: DFile;

  fileBaseUrl: string;

  constructor() {
    this.fileBaseUrl = environment.fileBaseUrl;
  }

  ngOnInit(): void {
  }

}
