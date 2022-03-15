import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AccessVideoComponent} from './access-video.component';

describe('AccessVideoComponent', () => {
  let component: AccessVideoComponent;
  let fixture: ComponentFixture<AccessVideoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AccessVideoComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccessVideoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
