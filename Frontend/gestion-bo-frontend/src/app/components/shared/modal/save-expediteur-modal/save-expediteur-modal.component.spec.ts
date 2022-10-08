import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveExpediteurModalComponent } from './save-expediteur-modal.component';

describe('SaveExpediteurModalComponent', () => {
  let component: SaveExpediteurModalComponent;
  let fixture: ComponentFixture<SaveExpediteurModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SaveExpediteurModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SaveExpediteurModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
