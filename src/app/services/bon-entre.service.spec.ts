import { TestBed } from '@angular/core/testing';

import { BonEntreService } from './bon-entre.service';

describe('BonEntreService', () => {
  let service: BonEntreService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BonEntreService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
