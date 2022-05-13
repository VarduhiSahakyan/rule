import { TestBed } from '@angular/core/testing';

import { AuthentmeansService } from './authentmeans.service';

describe('AuthentmeansService', () => {
  let service: AuthentmeansService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthentmeansService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
