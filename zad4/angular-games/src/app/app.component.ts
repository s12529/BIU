import { Component, OnInit } from '@angular/core';
import { Game } from './game';
import { GameService } from './game.service';
@Component({
  selector: 'my-app',
  template: `
    <h1>{{title}}</h1>
    <h2>My Games</h2>
    <ul class="games">
      <li *ngFor="let game of games"
        [class.selected]="game === selectedGame"
        (click)="onSelect(game)">
        <span class="badge">{{game.id}}</span> {{game.name}}
      </li>
    </ul>
    <my-game-detail [game]="selectedGame"></my-game-detail>
  `,
  styles: [`
    .selected {
      background-color: #CFD8DC !important;
      color: white;
    }
    .games {
      margin: 0 0 2em 0;
      list-style-type: none;
      padding: 0;
      width: 15em;
    }
    .games li {
      cursor: pointer;
      position: relative;
      left: 0;
      background-color: #EEE;
      margin: .5em;
      padding: .3em 0;
      height: 1.6em;
      border-radius: 4px;
    }
    .games li.selected:hover {
      background-color: #BBD8DC !important;
      color: white;
    }
    .games li:hover {
      color: #607D8B;
      background-color: #DDD;
      left: .1em;
    }
    .games .text {
      position: relative;
      top: -3px;
    }
    .games .badge {
      display: inline-block;
      font-size: small;
      color: white;
      padding: 0.8em 0.7em 0 0.7em;
      background-color: #607D8B;
      line-height: 1em;
      position: relative;
      left: -1px;
      top: -4px;
      height: 1.8em;
      margin-right: .8em;
      border-radius: 4px 0 0 4px;
    }
  `],
  providers: [GameService]
})
export class AppComponent implements OnInit {
  title = 'Tour of Games';
  games: Game[];
  selectedGame: Game;
  constructor(private gameService: GameService) { }
  getGames(): void {
    this.gameService.getGames().then(games => this.games = games);
  }
  ngOnInit(): void {
    this.getGames();
  }
  onSelect(game: Game): void {
    this.selectedGame = game;
  }
}