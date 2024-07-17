import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateReviewerEvaluationStepComponent } from './update-reviewer-evaluation-step.component';

describe('UpdateReviewerEvaluationStepComponent', () => {
  let component: UpdateReviewerEvaluationStepComponent;
  let fixture: ComponentFixture<UpdateReviewerEvaluationStepComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateReviewerEvaluationStepComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateReviewerEvaluationStepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
