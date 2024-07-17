import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeTeamMatesComponent } from './employee-team-mates.component';

describe('EmployeeTeamMatesComponent', () => {
  let component: EmployeeTeamMatesComponent;
  let fixture: ComponentFixture<EmployeeTeamMatesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EmployeeTeamMatesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EmployeeTeamMatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
