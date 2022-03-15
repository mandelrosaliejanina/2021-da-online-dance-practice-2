import {Component, Input, OnInit} from '@angular/core';
import {DFile} from "../../../../models/models";

@Component({
  selector: 'app-access-video',
  templateUrl: './access-video.component.html',
  styleUrls: ['./access-video.component.scss']
})
export class AccessVideoComponent implements OnInit {

  @Input()
  file!: DFile;

  constructor() {
  }

  ngOnInit(): void {
  }

}
