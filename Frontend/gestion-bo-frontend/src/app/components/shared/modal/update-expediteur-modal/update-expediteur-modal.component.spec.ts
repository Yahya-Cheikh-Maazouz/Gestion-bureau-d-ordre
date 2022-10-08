import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateExpediteurModalComponent } from './update-expediteur-modal.component';

describe('UpdateExpediteurModalComponent', () => {
  let component: UpdateExpediteurModalComponent;
  let fixture: ComponentFixture<UpdateExpediteurModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateExpediteurModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateExpediteurModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
