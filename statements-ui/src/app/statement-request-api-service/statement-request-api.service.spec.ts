import { TestBed } from '@angular/core/testing';

import { StatementRequestApiService } from './statement-request-api.service';

describe('StatementRequestApiService', () => {
  let service: StatementRequestApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StatementRequestApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
