import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeCompetenciesComponent } from './employee-competencies.component';

describe('EmployeeCompetenciesComponent', () => {
  let component: EmployeeCompetenciesComponent;
  let fixture: ComponentFixture<EmployeeCompetenciesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EmployeeCompetenciesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EmployeeCompetenciesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
