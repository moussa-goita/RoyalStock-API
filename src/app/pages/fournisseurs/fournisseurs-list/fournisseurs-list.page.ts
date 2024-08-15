import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { IonButton, IonButtons, IonCard, IonCardContent, IonContent, IonHeader, IonIcon, IonItem, IonLabel, IonMenuButton, IonModal, IonRouterLink, IonText, IonTextarea, IonTitle, IonToggle, IonToolbar } from '@ionic/angular/standalone';
import { addIcons } from 'ionicons';
import { briefcase, call, create, home, image, location, person, star, trash } from 'ionicons/icons';

interface Fournisseur {
  nom: string;
  adresse: string;
  telephone: string;
  service: string;
  rating: number;
}

@Component({
  selector: 'app-fournisseurs-list',
  standalone: true,
  imports: [IonModal, IonItem, IonToggle, IonLabel, IonTextarea, IonButton, 
    CommonModule,
    IonHeader,
    IonToolbar,IonMenuButton,
    IonTitle,
    IonButtons,
    IonMenuButton,
    IonContent,
    IonCard,
    IonCardContent,
    IonText,
    IonIcon,
    IonRouterLink
  ],
  templateUrl: './fournisseurs-list.page.html',
  styleUrls: ['./fournisseurs-list.page.scss']
})
export class FournisseursListPage  {

  // fournisseurs: Fournisseur[] = [];
  fournisseurs = [
    {
      nom: 'Moussa',
      adresse: 'Kalaban',
      telephone: '2222',
      service: 'Poisson',
      rating: 5,
      commentaire: '',
      isPublic: false
    }
  ];
  isModalOpen = false;
  imageUrl = '../../../../assets/WhatsApp Image 2024-02-24 at 1.14.06 AM.jpeg';
isPublic: any;
  constructor(private router: Router) { 
    addIcons({star, location,home, person, call,trash, briefcase,image, create})
  }
  openImageModal() {
    this.isModalOpen = true;
  }
  goToAddForm() {
    console.log('Navigation vers le formulaire');

    this.router.navigate(['/fournisseurs-form']);
    console.log('Navigation vers le Ok');
  }

  closeModal() {
    this.isModalOpen = false;
  }
  
  editFournisseur() {
    console.log('Modifier le fournisseur');
  }

  deleteFournisseur() {
    console.log('Supprimer le fournisseur');
  }
}
