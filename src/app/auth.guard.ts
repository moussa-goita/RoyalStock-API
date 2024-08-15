import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const currentUser = this.authService.currentUserValue;

    if (currentUser) {
      // Si l'utilisateur est connecté, vérifier s'il a le rôle requis
      const roles = route.data['roles'] as Array<string>;
      if (roles && roles.indexOf(currentUser.role) === -1) {
        // Si l'utilisateur n'a pas le rôle requis, rediriger vers une autre page (par exemple, une page d'erreur)
        this.router.navigate(['/access-denied']);
        return false;
      }
      // Autoriser l'accès si l'utilisateur a le rôle requis
      return true;
    }

    // Si l'utilisateur n'est pas connecté, rediriger vers la page de login
    this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
    return false;
  }
}
