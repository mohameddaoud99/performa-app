import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotifyUsersComponent } from './notify-users.component';

describe('NotifyUsersComponent', () => {
  let component: NotifyUsersComponent;
  let fixture: ComponentFixture<NotifyUsersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NotifyUsersComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NotifyUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
