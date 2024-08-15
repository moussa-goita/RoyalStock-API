import { TestBed } from '@angular/core/testing';

import { DetailSortieService } from './detail-sortie.service';

describe('DetailSortieService', () => {
  let service: DetailSortieService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DetailSortieService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
