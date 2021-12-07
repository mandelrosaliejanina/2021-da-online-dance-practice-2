import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-dance-level',
  templateUrl: './dance-level.component.html',
  styleUrls: ['./dance-level.component.css']
})
export class DanceLevelComponent implements OnInit {
  @Input() levelname!: string;
  @Output() levelSelected: EventEmitter<string> = new EventEmitter<string>();

  name!: string;

  constructor() { }

  ngOnInit(): void {
    this.name = this.levelname;

  }

  // tslint:disable-next-line:typedef
  levels(s: string) {
    // @ts-ignore
    document.getElementById('fname').value = s;
    this.levelSelected.emit(s);
  }
}

