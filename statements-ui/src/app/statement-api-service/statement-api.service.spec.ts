import { TestBed } from '@angular/core/testing';

import { StatementApiService } from './statement-api.service';

describe('StatementApiService', () => {
  let service: StatementApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StatementApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
