import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AccesstokenHomeComponent} from './accesstoken-home.component';

describe('AccesstokenHomeComponent', () => {
  let component: AccesstokenHomeComponent;
  let fixture: ComponentFixture<AccesstokenHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AccesstokenHomeComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccesstokenHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
