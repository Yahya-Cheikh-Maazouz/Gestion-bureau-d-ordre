import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageErrorsComponent } from './manage-errors.component';

describe('ManageErrorsComponent', () => {
  let component: ManageErrorsComponent;
  let fixture: ComponentFixture<ManageErrorsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageErrorsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageErrorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
