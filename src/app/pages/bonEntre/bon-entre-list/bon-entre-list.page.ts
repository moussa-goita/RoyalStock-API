import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { IonButtons, IonContent, IonHeader, IonMenuButton, IonTitle, IonToolbar } from '@ionic/angular/standalone';

@Component({
  selector: 'app-bon-entre-list',
  templateUrl: './bon-entre-list.page.html',
  styleUrls: ['./bon-entre-list.page.scss'],
  standalone: true,
  imports: [IonButtons, IonContent, IonHeader, IonTitle, IonToolbar, IonMenuButton, CommonModule, FormsModule]
})
export class BonEntreListPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
