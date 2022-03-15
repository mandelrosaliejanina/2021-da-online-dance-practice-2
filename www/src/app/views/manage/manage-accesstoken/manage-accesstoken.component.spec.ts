import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ManageAccesstokenComponent} from './manage-accesstoken.component';

describe('ManageAccesstokenComponent', () => {
  let component: ManageAccesstokenComponent;
  let fixture: ComponentFixture<ManageAccesstokenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ManageAccesstokenComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageAccesstokenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
