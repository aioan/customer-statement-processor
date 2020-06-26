import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StatementRequestDetailsComponent } from './statement-request-details.component';

describe('StatementErrorDetailsComponent', () => {
  let component: StatementRequestDetailsComponent;
  let fixture: ComponentFixture<StatementRequestDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StatementRequestDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StatementRequestDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
