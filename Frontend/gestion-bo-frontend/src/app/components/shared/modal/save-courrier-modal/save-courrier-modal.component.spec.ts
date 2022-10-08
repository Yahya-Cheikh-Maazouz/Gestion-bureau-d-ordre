import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveCourrierModalComponent } from './save-courrier-modal.component';

describe('SaveCourrierModalComponent', () => {
  let component: SaveCourrierModalComponent;
  let fixture: ComponentFixture<SaveCourrierModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SaveCourrierModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SaveCourrierModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
