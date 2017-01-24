import {Component, OnInit, Input} from '@angular/core';
import {Gra} from "../gra";

@Component({
  selector: 'app-gra-detail',
  templateUrl: './gra-detail.component.html'
})
export class GraDetailComponent implements OnInit {
  @Input()
  gra: Gra;

  ngOnInit(){

  }
}
