import {ComponentFixture, TestBed} from '@angular/core/testing';

import {DetailedAccesstokenComponent} from './detailed-accesstoken.component';

describe('DetailedAccesstokenComponent', () => {
  let component: DetailedAccesstokenComponent;
  let fixture: ComponentFixture<DetailedAccesstokenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DetailedAccesstokenComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailedAccesstokenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
