import { TestBed } from '@angular/core/testing';

import { DetailEntreeService } from './detail-entree.service';

describe('DetailEntreeService', () => {
  let service: DetailEntreeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DetailEntreeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
