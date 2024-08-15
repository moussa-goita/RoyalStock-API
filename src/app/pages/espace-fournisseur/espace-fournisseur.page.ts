import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar } from '@ionic/angular/standalone';

@Component({
  selector: 'app-espace-fournisseur',
  templateUrl: './espace-fournisseur.page.html',
  styleUrls: ['./espace-fournisseur.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule]
})
export class EspaceFournisseurPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
