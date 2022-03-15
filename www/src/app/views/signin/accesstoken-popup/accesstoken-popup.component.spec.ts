import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AccesstokenPopupComponent} from './accesstoken-popup.component';

describe('AccesstokenPopupComponent', () => {
  let component: AccesstokenPopupComponent;
  let fixture: ComponentFixture<AccesstokenPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AccesstokenPopupComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccesstokenPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
