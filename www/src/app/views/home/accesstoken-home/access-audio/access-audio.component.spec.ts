import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AccessAudioComponent} from './access-audio.component';

describe('AccessAudioComponent', () => {
  let component: AccessAudioComponent;
  let fixture: ComponentFixture<AccessAudioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AccessAudioComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccessAudioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
