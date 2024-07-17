import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCompetenciesComponent } from './user-competencies.component';

describe('UserCompetenciesComponent', () => {
  let component: UserCompetenciesComponent;
  let fixture: ComponentFixture<UserCompetenciesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UserCompetenciesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UserCompetenciesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
