import {Component, Input, OnInit} from '@angular/core';
import {DFile} from "../../../../models/models";

@Component({
  selector: 'app-audio',
  templateUrl: './audio.component.html',
  styleUrls: ['./audio.component.scss']
})
export class AudioComponent implements OnInit {

  @Input()
  file!: DFile;

  constructor() {
  }

  ngOnInit(): void {
  }

}
