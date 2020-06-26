import { TestBed } from '@angular/core/testing';

import { StatementErrorApiService } from './statement-error-api.service';

describe('StatementErrorApiService', () => {
  let service: StatementErrorApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StatementErrorApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
