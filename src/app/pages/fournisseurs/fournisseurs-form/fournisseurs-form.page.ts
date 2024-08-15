import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import {
  IonButton,
  IonButtons,
  IonCol,
  IonContent,
  IonFooter,
  IonHeader,
  IonItem,
  IonLabel, IonMenuButton,
  IonRow,
  IonTitle,
  IonToolbar
} from '@ionic/angular/standalone';
@Component({
  selector: 'app-fournisseur-form',
  templateUrl: './fournisseurs-form.page.html',
  styleUrls: ['./fournisseurs-form.page.scss'],
  standalone: true,
  imports: [IonButtons, IonCol, IonFooter, IonButton, IonHeader, 
    CommonModule, 
    FormsModule, 
    ReactiveFormsModule, IonItem,
    IonFooter,IonContent,
    IonLabel,IonMenuButton,
    IonTitle,
    IonToolbar,
    IonRow,
    IonCol,
  ],
})
export class FournisseursFormPage {
  fournisseurForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.fournisseurForm = this.fb.group({
      nom: ['', Validators.required],
      adresse: ['', Validators.required],
      telephone: ['', Validators.required],
      service: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.fournisseurForm.valid) {
      const fournisseurData = this.fournisseurForm.value;
      console.log('Fournisseur ajouté:', fournisseurData);
    }
  }

  onCancel() {
    this.fournisseurForm.reset();
  }
}
