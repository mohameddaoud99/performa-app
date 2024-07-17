import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateEmloyeeEvaluationComponent } from './update-emloyee-evaluation.component';

describe('UpdateEmloyeeEvaluationComponent', () => {
  let component: UpdateEmloyeeEvaluationComponent;
  let fixture: ComponentFixture<UpdateEmloyeeEvaluationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateEmloyeeEvaluationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateEmloyeeEvaluationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
