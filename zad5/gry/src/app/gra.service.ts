import { Injectable } from '@angular/core';
import { Gra } from './gra';
import { GRY } from './mroczne-gry';

@Injectable()
export class GraService {
  getGry(): Promise<Gra[]> {
    return Promise.resolve(GRY);
  }
}
