import { TestBed } from '@angular/core/testing';

import { StatementReportApiService } from './statement-report-api.service';

describe('StatementReportApiService', () => {
  let service: StatementReportApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StatementReportApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
