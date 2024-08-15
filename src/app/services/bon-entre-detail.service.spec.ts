import { TestBed } from '@angular/core/testing';

import { BonEntreDetailService } from './bon-entre-detail.service';

describe('BonEntreDetailService', () => {
  let service: BonEntreDetailService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BonEntreDetailService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
