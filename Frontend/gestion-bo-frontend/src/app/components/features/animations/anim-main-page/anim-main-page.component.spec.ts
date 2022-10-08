import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimMainPageComponent } from './anim-main-page.component';

describe('AnimMainPageComponent', () => {
  let component: AnimMainPageComponent;
  let fixture: ComponentFixture<AnimMainPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnimMainPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnimMainPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
