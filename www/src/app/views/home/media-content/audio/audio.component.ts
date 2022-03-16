import {Component, Input, OnInit} from '@angular/core';
import {DFile} from "../../../../models/models";
import {environment} from "../../../../../environments/environment";

@Component({
  selector: 'app-audio',
  templateUrl: './audio.component.html',
  styleUrls: ['./audio.component.scss']
})
export class AudioComponent implements OnInit {

  @Input()
  file!: DFile;

  fileBaseUrl: string;

  constructor() {
    this.fileBaseUrl = environment.fileBaseUrl;
  }

  ngOnInit(): void {
  }

}
