import {Component, Input, OnInit} from '@angular/core';
import {DFile} from "../../../../models/models";

@Component({
  selector: 'app-access-audio',
  templateUrl: './access-audio.component.html',
  styleUrls: ['./access-audio.component.scss']
})
export class AccessAudioComponent implements OnInit {

  @Input()
  file!: DFile;

  constructor() {
  }

  ngOnInit(): void {
  }

}
