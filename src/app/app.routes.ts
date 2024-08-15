import { Routes } from '@angular/router';
import {AuthGuard} from "./auth.guard";

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: 'folder/:id',
    loadComponent: () =>
      import('./folder/folder.page').then((m) => m.FolderPage),
  },

  {
    path: 'bon-entre-form',
    loadComponent: () => import('./pages/bonEntre/bon-entre-form/bon-entre-form.page').then( m => m.BonEntreFormPage), data: { roles: ['MANAGER']}
  },
  {
    path: 'bon-entre-list',
    loadComponent: () => import('./pages/bonEntre/bon-entre-list/bon-entre-list.page').then( m => m.BonEntreListPage), data: { roles: ['MANAGER']}
  },
  {
    path: 'bon-entre-detail',
    loadComponent: () => import('./pages/bonEntre/bon-entre-detail/bon-entre-detail.page').then( m => m.BonEntreDetailPage), data: { roles: ['MANAGER']}
  },
  {
    path: 'bon-sortie-detail',
    loadComponent: () => import('./pages/bonSortie/bon-sortie-detail/bon-sortie-detail.page').then( m => m.BonSortieDetailPage), data: { roles: ['VENDEUR', 'MANAGER']}
  },
  {
    path: 'bon-sortie-list',
    loadComponent: () => import('./pages/bonSortie/bon-sortie-list/bon-sortie-list.page').then( m => m.BonSortieListPage), data: { roles: ['VENDEUR', 'MANAGER']}
  },
  {
    path: 'bon-sortie-form',
    loadComponent: () => import('./pages/bonSortie/bon-sortie-form/bon-sortie-form.page').then( m => m.BonSortieFormPage), data: { roles: ['VENDEUR', 'MANAGER']}
  },
  {
    path: 'dashboard',
    loadComponent: () => import('./pages/dashboard/dashboard/dashboard.page').then( m => m.DashboardPage), data: { roles: ['ADMIN', 'VENDEUR', 'MANAGER']}
  },
  {
    path: 'fournisseurs-list',
    loadComponent: () => import('./pages/fournisseurs/fournisseurs-list/fournisseurs-list.page').then( m => m.FournisseursListPage), data: { roles: ['MANAGER']}
  },
  {
    path: 'fournisseurs-form',
    loadComponent: () => import('./pages/fournisseurs/fournisseurs-form/fournisseurs-form.page').then( m => m.FournisseursFormPage), data: { roles: ['MANAGER']}
  },
  {
    path: 'fournisseurs-espace',
    loadComponent: () => import('./pages/fournisseurs/fournisseurs-espace/fournisseurs-espace.page').then( m => m.FournisseursEspacePage), data: { roles: ['MANAGER']}
  },
  {
    path: 'notifications-list',
    loadComponent: () => import('./pages/notifications/notifications-list/notifications-list.page').then( m => m.NotificationsListPage), data: { roles: ['ADMIN', 'VENDEUR', 'MANAGER']}
  },
  {
    path: 'user-profile',
    loadComponent: () => import('./pages/user-profile/user-profile.page').then( m => m.UserProfilePage), data: { roles: ['ADMIN', 'VENDEUR', 'MANAGER']}
  },
  {
    path: 'espace-fournisseur',
    loadComponent: () => import('./pages/espace-fournisseur/espace-fournisseur.page').then( m => m.EspaceFournisseurPage), canActivate: [AuthGuard], data: { roles: ['VENDEUR', 'MANAGER']}
  },
  {
    path: 'entrepot',
    loadComponent: () => import('./pages/entrepot/entrepot.page').then( m => m.EntrepotPage), canActivate: [AuthGuard], data: { roles: ['ADMIN']}
  },
  {
    path: 'rapport',
    loadComponent: () => import('./pages/rapport/rapport.page').then( m => m.RapportPage), canActivate: [AuthGuard], data: { roles: ['MANAGER']}
  },
  {
    path: 'produit',
    loadComponent: () => import('./pages/produit/produit.page').then( m => m.ProduitPage), canActivate: [AuthGuard], data: { roles: ['VENDEUR', 'MANAGER']}
  },
  {
    path: 'user-profile',
    loadComponent: () => import('./pages/user-profile/user-profile.page').then( m => m.UserProfilePage), canActivate: [AuthGuard], data: { roles: ['ADMIN', 'MANAGER', 'VENDEUR']}
  },
  {
    path: 'login',
    loadComponent: () => import('./pages/login/login.page').then( m => m.LoginPage)
  },
];
