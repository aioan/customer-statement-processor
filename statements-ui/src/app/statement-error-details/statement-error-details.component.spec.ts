import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StatementErrorDetailsComponent } from './statement-error-details.component';

describe('StatementErrorDetailsComponent', () => {
  let component: StatementErrorDetailsComponent;
  let fixture: ComponentFixture<StatementErrorDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StatementErrorDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StatementErrorDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
