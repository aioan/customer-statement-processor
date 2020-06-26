import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StatementRequestListComponent } from './statement-request-list.component';

describe('StatementRequestListComponent', () => {
  let component: StatementRequestListComponent;
  let fixture: ComponentFixture<StatementRequestListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StatementRequestListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StatementRequestListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
