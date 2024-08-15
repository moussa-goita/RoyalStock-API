import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import '@formatjs/intl-relativetimeformat/polyfill';
import { format } from 'date-fns';

import {
  IonBackButton,
  IonButton,
  IonButtons,
  IonCol,
  IonContent,
  IonFooter,
  IonHeader,
  IonInput,
  IonItem,
  IonLabel,
  IonMenuButton,
  IonRow,
  IonSelect,
  IonSelectOption,
  IonText,
  IonTitle,
  IonToolbar
} from '@ionic/angular/standalone';
import { BonSortie } from 'src/app/models/bon-sortie';
import { DetailSortie } from 'src/app/models/detail-sortie';
import { Motif } from 'src/app/models/motif';
import { Utilisateur } from 'src/app/models/utilisateur';
import { AuthService } from 'src/app/services/auth.service';
import { BonSortieService } from 'src/app/services/bon-sortie.service';
import { MotifService } from 'src/app/services/motif.service';

@Component({
  selector: 'app-bon-sortie-form',
  templateUrl: './bon-sortie-form.page.html',
  styleUrls: ['./bon-sortie-form.page.scss'],
  standalone: true,
  imports: [
    IonText, 
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    IonMenuButton,
    RouterModule,
    IonButton,
    IonButtons,
    IonSelectOption,
    IonBackButton,
    IonContent,
    IonHeader,
    IonInput,
    IonItem,
    IonFooter,
    IonLabel,
    IonTitle,
    IonSelect,
    IonToolbar,
    IonRow,
    IonCol,
  ],
})
export class BonSortieFormPage implements OnInit {
  bonSortieForm: FormGroup;
  detailSortie: DetailSortie[] = [];
  bonSortie: BonSortie = {} as BonSortie;
  motifs: Motif[] = [];
  selectedMotifId: number | any;
  isEditMode: boolean = false;
  successMessage: string = '';
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private bonSortieService: BonSortieService,
    private authService: AuthService,
    private motifService: MotifService
  ) {
    this.bonSortieForm = this.fb.group({
      date_sortie: ['', Validators.required],
      motifs: ['', Validators.required],
      entrepot_id: ['', Validators.required],
      utilisateur_id: ['', Validators.required],      
    });
  }

  ngOnInit(): void {
    console.log('ngOnInit appelé');
    this.bonSortie.utilisateur = {
      id: this.authService.currentUserValue?.id,
      username: this.authService.currentUserValue?.username
    } as Utilisateur;
    this.loadMotifs();
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.loadBonSortieById(+id);
    }
  }

  loadMotifs(): void {
    this.motifService.getMotifs().subscribe(
      (data) => {
        this.motifs = data;
        console.log('Motifs reçus:', data);
      },
      (error) => {
        console.error('Error loading motifs:', error);
        this.errorMessage = 'Erreur lors du chargement des motifs.';
        setTimeout(() => (this.errorMessage = ''), 3000);
      }
    );
  }

  get motifControl(): FormControl {
    return this.bonSortieForm.get('motifs') as FormControl;
  }

  loadBonSortieById(id: number): void {
    this.bonSortieService.getBonSortieById(id).subscribe(
      (data) => {
        this.bonSortie = data;
        this.selectedMotifId = data.motif ? data.motif.id : null;
        this.bonSortieForm.patchValue({
          date_sortie: this.bonSortie.dateSortie,
          motifs: this.selectedMotifId,
          // entrepot_id: this.bonSortie.entrepot?.id,
          // utilisateur_id: this.bonSortie.utilisateur?.id,
        });
        console.log('Bon de sortie chargé:', data);
      },
      (error) => {
        console.error('Error loading bon de sortie:', error);
        this.errorMessage = 'Erreur lors du chargement du bon de sortie.';
        setTimeout(() => (this.errorMessage = ''), 3000);
      }
    );
  }

  onSubmit(): void {
    console.log('onSubmit appelé');
    const formData = this.bonSortieForm.value;
    if (!this.bonSortieForm.valid) {
      this.errorMessage = 'Veuillez remplir tous les champs requis.';
      setTimeout(() => (this.errorMessage = ''), 3000);
      return;
    }

    const selectedMotif = this.motifs.find((motif) => motif.id === this.selectedMotifId);
    this.bonSortie.detailsSorties = this.detailSortie;
    this.bonSortie.motif = { id: this.selectedMotifId } as Motif;

    const formattedBonSortie = {
      ...this.bonSortie,
      date_sortie: this.bonSortie.dateSortie
        ? format(new Date(this.bonSortie.dateSortie), 'MM-dd-yyyy')
        : null,
    };

    if (this.isEditMode) {
      this.bonSortieService.updateBonSortie(this.bonSortie.id, formattedBonSortie).subscribe(
        () => {
          this.successMessage = 'Bon de Sortie mis à jour avec succès!';
          setTimeout(() => (this.successMessage = ''), 3000);
          setTimeout(() => this.router.navigate(['/bon-sortie']), 3000);
        },
        (error) => {
          console.error('Error updating bon de sortie:', error);
          this.errorMessage = 'Erreur lors de la mise à jour du bon de sortie.';
          setTimeout(() => (this.errorMessage = ''), 3000);
        }
      );
    } else {
      const currentUserEmail = this.authService.currentUserValue?.email;
      if (!currentUserEmail) {
        this.errorMessage = 'Erreur : utilisateur non authentifié.';
        setTimeout(() => (this.errorMessage = ''), 3000);
        return;
      }

      this.bonSortieService.createBonSortie(formattedBonSortie, currentUserEmail).subscribe(
        () => {
          this.successMessage = 'Bon de Sortie créé avec succès!';
          setTimeout(() => (this.successMessage = ''), 3000);
          setTimeout(() => this.router.navigate(['/bon-sortie']), 3000);
        },
        (error) => {
          console.error('Error creating bon de sortie:', error);
          this.errorMessage = 'Erreur lors de la création du bon de sortie.';
          setTimeout(() => (this.errorMessage = ''), 3000);
        }
      );
    }
  }

  onCancel() {
    this.router.navigate(['/bon-sortie-list']);
  }
}
