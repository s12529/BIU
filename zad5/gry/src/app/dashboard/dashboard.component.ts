import { Component, Output, EventEmitter, Inject } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html'
})

export class DashboardComponent {

  @Output()
  addToList = new EventEmitter();

  form: FormGroup;

  constructor(@Inject(FormBuilder) fb: FormBuilder) {
    this.form = fb.group({
      name: ["", [Validators.minLength(4), Validators.maxLength(10)]]
    });
  }

  onAddGra(): void {
    if (this.form.dirty && this.form.valid) {
      this.addToList.emit({gra: this.form.value});
    }
  }
}
