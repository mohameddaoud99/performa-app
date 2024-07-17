import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewerStepComponent } from './reviewer-evaluation-step.component';

describe('ReviewerStepComponent', () => {
  let component: ReviewerStepComponent;
  let fixture: ComponentFixture<ReviewerStepComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ReviewerStepComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReviewerStepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
