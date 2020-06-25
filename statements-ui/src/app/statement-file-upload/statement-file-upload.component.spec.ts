import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StatementFileUploadComponent } from './statement-file-upload.component';

describe('StatementFileUploadComponent', () => {
  let component: StatementFileUploadComponent;
  let fixture: ComponentFixture<StatementFileUploadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StatementFileUploadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StatementFileUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
