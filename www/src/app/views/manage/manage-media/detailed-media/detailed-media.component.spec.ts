import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailedMediaComponent } from './detailed-media.component';

describe('DetailedMediaComponent', () => {
  let component: DetailedMediaComponent;
  let fixture: ComponentFixture<DetailedMediaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailedMediaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailedMediaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
