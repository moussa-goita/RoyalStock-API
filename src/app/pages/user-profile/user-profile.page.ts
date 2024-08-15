import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  IonButton, IonCard, IonCardContent, IonCardHeader, IonCardTitle,
  IonContent,
  IonHeader,
  IonInput,
  IonItem, IonLabel,
  IonList,
  IonTitle,
  IonToolbar
} from '@ionic/angular/standalone';
import { UtilisateurService } from "../../services/utilisateur.service";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.page.html',
  styleUrls: ['./user-profile.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonItem, IonInput, IonButton, IonList, IonCardTitle, IonCardHeader, IonCardContent, IonCard, IonLabel]
})
export class UserProfilePage implements OnInit {

  utilisateur: any = {}; // Define the utilisateur object
  newPassword: string = '';
  confirmPassword: string = '';
  errorMessage: string = '';

  constructor(private UtilisateurService: UtilisateurService, private authService: AuthService) {}

  ngOnInit(): void {
    this.loadUserProfile();
  }

  loadUserProfile(): void {
    const currentUser = this.authService.currentUserValue;
    if (currentUser && currentUser.id) {
      this.UtilisateurService.getUtilisateurById(currentUser.id).subscribe(
        (data: any) => {
          this.utilisateur = data;
        },
        (error) => {
          console.error('Error fetching user profile', error);
        }
      );
    }
  }

  updateProfile(): void {
    if (this.newPassword && this.newPassword !== this.confirmPassword) {
      this.errorMessage = 'Les mots de passe ne correspondent pas.';
      return;
    }

    const updateData = {
      ...this.utilisateur,
      password: this.newPassword ? this.newPassword : undefined
    };

    this.UtilisateurService.updateUtilisateur(this.utilisateur.id, this.utilisateur).subscribe(
      (data) => {
        console.log('User profile updated successfully', data);
      },
      (error) => {
        console.error('Error updating user profile', error);
      }
    );
  }

  get currentUser() {
    return this.authService.currentUserValue;
  }

  logout(): void {
    this.authService.logout();
  }

}
