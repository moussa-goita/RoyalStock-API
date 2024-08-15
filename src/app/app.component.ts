import {CommonModule} from '@angular/common';
import {Component} from '@angular/core';
import {Router, RouterLink, RouterLinkActive} from '@angular/router';
import {
  IonApp,
  IonButtons,
  IonContent,
  IonHeader,
  IonIcon,
  IonItem,
  IonItemDivider,
  IonLabel,
  IonList,
  IonListHeader,
  IonMenu,
  IonMenuButton,
  IonMenuToggle,
  IonNote,
  IonRouterLink,
  IonRouterOutlet,
  IonSplitPane,
  IonTitle,
  IonToggle,
  IonToolbar
} from '@ionic/angular/standalone';
import {addIcons} from 'ionicons';
import {
  homeOutline,
  homeSharp,
  cubeOutline,
  cubeSharp,
  enterOutline,
  enterSharp,
  exitOutline,
  exitSharp,
  pricetagsOutline,
  pricetagsSharp,
  keyOutline,
  keySharp,
  businessOutline,
  businessSharp,
  peopleOutline,
  peopleSharp,
  personOutline,
  personSharp,
  compassOutline,
  notificationsOutline,
  receiptOutline,
  statsChartOutline,
  bookmarksOutline,
  compassSharp,
  notificationsSharp,
  receiptSharp,
  statsChartSharp,
  bookmarksSharp, invertModeOutline, invertModeSharp
} from 'ionicons/icons';
import {AuthService} from "./services/auth.service";
import {Platform} from '@ionic/angular';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
  standalone: true,
  imports: [IonToggle, IonButtons, IonToolbar, IonToolbar, IonHeader, IonTitle, RouterLink, RouterLinkActive, CommonModule, IonMenuButton, IonApp, IonSplitPane, IonMenu, IonContent, IonList, IonListHeader, IonNote, IonMenuToggle, IonItem, IonIcon, IonLabel, IonRouterLink, IonRouterOutlet, IonItemDivider],
})
export class AppComponent {
  public appPages: Array<{ title: string, url: string, icon: string, role: string[] }> = [];
  public currentUserRole: string | any;

  constructor(public authService: AuthService, private router: Router) {
    this.authService.currentUser.subscribe(user => {
      this.currentUserRole = user?.role;
      this.initializeAppPages();
    });

    addIcons({
      homeOutline, homeSharp,
      cubeOutline, cubeSharp,
      enterOutline, enterSharp,
      exitOutline, exitSharp,
      pricetagsOutline, pricetagsSharp,
      keyOutline, keySharp,
      businessOutline, businessSharp,
      peopleOutline, peopleSharp,
      personOutline, personSharp,
      statsChartOutline, receiptOutline, bookmarksOutline,
      compassOutline, notificationsOutline, compassSharp,
      notificationsSharp, receiptSharp, statsChartSharp,
      bookmarksSharp, invertModeOutline, invertModeSharp
    });
  }

  initializeAppPages() {
    if (!this.currentUserRole) {
      this.router.navigate(['/login']);
      return;
    }

    this.appPages = [
      {title: 'Dashboard', url: '/dashboard', icon: 'home', role: ['ADMIN', 'MANAGER', 'VENDEUR']},
      {title: 'Bon Entrée', url: '/bon-entre-list', icon: 'enter', role: ['MANAGER']},
      {title: 'Bon Sortie', url: '/bon-sortie-list', icon: 'exit', role: ['VENDEUR', 'MANAGER']},
      {title: 'Fournisseurs', url: '/fournisseurs-list', icon: 'people', role: ['VENDEUR', 'MANAGER']},
      {title: 'Fournisseurs-Espace', url: '/fournisseurs-espace', icon: 'bookmarks', role: ['VENDEUR', 'MANAGER']},
      {title: 'Produits', url: '/produit', icon: 'cube', role: ['VENDEUR', 'MANAGER']},
      {title: 'Entrepôt', url: '/entrepot', icon: 'compass', role: ['ADMIN']},
      {title: 'Notification', url: '/notifications-list', icon: 'notifications', role: ['ADMIN', 'MANAGER', 'USER']},
      {title: 'Rapport', url: '/rapport', icon: 'receipt', role: ['MANAGER']},
      {title: 'Mon Profil', url: '/user-profile', icon: 'person', role: ['ADMIN', 'MANAGER', 'VENDEUR']},
    ].filter(page => page.role.includes(this.currentUserRole));
  }

  toggleDarkMode(event: any) {
    document.body.classList.toggle('dark', event.detail.checked);
  }
}
