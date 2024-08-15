import { TestBed } from '@angular/core/testing';

import { BonSortieDetailService } from './bon-sortie-detail.service';

describe('BonSortieDetailService', () => {
  let service: BonSortieDetailService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BonSortieDetailService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
