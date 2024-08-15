import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { IonButtons, IonContent, IonHeader, IonMenuButton, IonTitle, IonToolbar } from '@ionic/angular/standalone';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.page.html',
  styleUrls: ['./dashboard.page.scss'],
  standalone: true,
  imports: [IonButtons, IonContent, IonMenuButton, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule]
})
export class DashboardPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
