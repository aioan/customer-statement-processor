import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StatementReportListComponent } from './statement-report-list.component';

describe('StatementReportListComponent', () => {
  let component: StatementReportListComponent;
  let fixture: ComponentFixture<StatementReportListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StatementReportListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StatementReportListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
