import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import {
  IonButton,
  IonButtons, IonCol, IonContent, IonFooter, IonHeader,
  IonIcon,
  IonInput,
  IonItem, IonLabel, IonMenuButton, IonRow,
  IonSelect,
  IonSelectOption,
  IonTitle, IonToolbar
} from '@ionic/angular/standalone';
import { addIcons } from 'ionicons';
import { camera, qrCode } from 'ionicons/icons';

@Component({
  selector: 'app-bon-sortie-detail',
  templateUrl: './bon-sortie-detail.page.html',
  styleUrls: ['./bon-sortie-detail.page.scss'],
  standalone: true,
  imports: [IonIcon, 
    CommonModule,
    ReactiveFormsModule,
    IonButton,
    IonButtons,
    IonMenuButton,
    IonContent,
    IonHeader,
    IonInput,
    IonItem,
    IonLabel,
    IonSelect,
    IonSelectOption,
    IonTitle,
    IonToolbar,
    IonFooter,
    IonRow,
    IonCol,IonIcon,
  ],
})
export class BonSortieDetailPage {
  detailForm: FormGroup;
  constructor(private fb: FormBuilder, private router: Router) {
    this.detailForm = this.fb.group({
      produit: ['', Validators.required],
      quantite: ['', [Validators.required, Validators.min(1)]],
      prix: ['', [Validators.required, Validators.min(0)]],
    });
    addIcons({
      qrCode, camera
     });
  }

  onSubmit() {
    if (this.detailForm.valid) {
      const detailData = this.detailForm.value;
      console.log('Détail soumis :', detailData);

      // this.router.navigate(['/some-page']);
    } else {
      console.log('Formulaire invalide');
    }
  }

  onCancel() {
    this.router.navigate(['/bon-sortie-list']); 
  }
}
