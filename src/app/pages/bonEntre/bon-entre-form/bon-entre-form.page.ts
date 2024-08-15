import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar } from '@ionic/angular/standalone';

@Component({
  selector: 'app-bon-entre-form',
  templateUrl: './bon-entre-form.page.html',
  styleUrls: ['./bon-entre-form.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule]
})
export class BonEntreFormPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
