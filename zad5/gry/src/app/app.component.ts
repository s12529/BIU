import { Component } from '@angular/core';
import {Gra} from "./gra";
import {GRY} from './selected-gry';
@Component({
  selector: 'my-app',
  templateUrl: './app.component.html'
})
export class AppComponent {
  title = 'Play It!';
  gry = GRY;
  selectedGra: Gra;
  isForm: number = 1;
  gra: Gra={id: 1, name: '', vote: 0, likes: false};

  onSelect(gra: Gra): void {
    this.selectedGra = gra;
  }

  onVote(gra:Gra):void{
    if (gra.vote > 0 && gra.likes != true)
    {
      gra.vote++;
      gra.likes = true;
    }
  }

  unlike(gra: Gra): void {
    if (gra.vote >= 1 && gra.likes != true)
    {
      gra.vote--;
      gra.likes = true;
    }
  }

  onSort():void{
    function compare(a,b) {
      if (a.vote < b.vote)
        return 1;
      if (a.vote > b.vote)
        return -1;
      return 0;
    }

    GRY.sort(compare);
  }

  onAddGra(event):void{
    this.gry.push(new Gra(this.gry.length+1, event.gra.name) );
  }

  onShowForm(): void{
      this.isForm*=-1;
  }
}
