import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { IonButtons, IonContent, IonHeader, IonMenuButton, IonTitle, IonToolbar } from '@ionic/angular/standalone';

@Component({
  selector: 'app-fournisseurs-espace',
  templateUrl: './fournisseurs-espace.page.html',
  styleUrls: ['./fournisseurs-espace.page.scss'],
  standalone: true,
  imports: [IonButtons, IonContent, IonHeader, IonMenuButton, IonTitle, IonToolbar, CommonModule, FormsModule]
})
export class FournisseursEspacePage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
