import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCourrierModalComponent } from './update-courrier-modal.component';

describe('UpdateCourrierModalComponent', () => {
  let component: UpdateCourrierModalComponent;
  let fixture: ComponentFixture<UpdateCourrierModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateCourrierModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCourrierModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
