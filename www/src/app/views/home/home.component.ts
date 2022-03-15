import {Component, OnInit} from '@angular/core';
import {BackendService} from "../../services/backend.service";
import {Course, Level} from "../../models/models";

@Component({
  selector: 'app-level',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  levels: Level[] | null;
  courses: Course[] | null;
  selectedLevel: Level | null;
  selectedCourse: Course | null;

  constructor(private readonly backend: BackendService) {
    this.levels = null;
    this.courses = null;
    this.selectedCourse = null;
    this.selectedLevel = null;
  }

  ngOnInit(): void {
    this.backend.get<Level[]>('level').then(value => {
      this.levels = value;
      this.selectedLevel = this.levels[0];
    });
    this.backend.get<Course[]>('course').then(value => this.courses = value);
  }


  selectLevel(level: Level): void {
    this.selectedLevel = level;
    this.selectedCourse = null;
  }

  selectCourse(course: Course): void {
    this.selectedCourse = course;
  }

  getCoursesByLevel(selectedLevel: Level | null): Course[] {
    if (selectedLevel) {
      return this.courses?.filter(c => c.level.id === selectedLevel.id) || [];
    }
    return [];
  }
}
