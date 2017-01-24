/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { GraService } from './gra.service';

describe('GraService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GraService]
    });
  });

  it('should ...', inject([GraService], (service: GraService) => {
    expect(service).toBeTruthy();
  }));
});
