import { TestBed } from '@angular/core/testing';

import { ParticipationserviceService } from './participationservice.service';

describe('ParticipationserviceService', () => {
  let service: ParticipationserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParticipationserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
