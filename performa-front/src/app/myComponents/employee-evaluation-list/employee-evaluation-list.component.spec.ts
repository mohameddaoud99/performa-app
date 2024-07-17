import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeEvaluationListComponent } from './employee-evaluation-list.component';

describe('EmployeeEvaluationListComponent', () => {
  let component: EmployeeEvaluationListComponent;
  let fixture: ComponentFixture<EmployeeEvaluationListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EmployeeEvaluationListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EmployeeEvaluationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
