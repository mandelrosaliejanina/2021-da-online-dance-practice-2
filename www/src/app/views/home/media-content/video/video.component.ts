import {Component, Input, OnInit} from '@angular/core';
import {DFile} from "../../../../models/models";

@Component({
  selector: 'app-video',
  templateUrl: './video.component.html',
  styleUrls: ['./video.component.scss']
})
export class VideoComponent implements OnInit {

  @Input()
  file!: DFile;

  constructor() {
  }

  ngOnInit(): void {
  }

}
