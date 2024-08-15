import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar } from '@ionic/angular/standalone';

@Component({
  selector: 'app-entrepot',
  templateUrl: './entrepot.page.html',
  styleUrls: ['./entrepot.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule]
})
export class EntrepotPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
