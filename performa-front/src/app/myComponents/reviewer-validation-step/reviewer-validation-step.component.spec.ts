import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewerValidationStepComponent } from './reviewer-validation-step.component';

describe('ReviewerValidationStepComponent', () => {
  let component: ReviewerValidationStepComponent;
  let fixture: ComponentFixture<ReviewerValidationStepComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ReviewerValidationStepComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReviewerValidationStepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
