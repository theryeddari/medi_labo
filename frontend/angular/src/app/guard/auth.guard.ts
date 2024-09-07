import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';
import {Observable, of} from 'rxjs';


export const authGuard: CanActivateFn = (route, state): Observable<boolean> | Promise<boolean> | boolean => {
  // Obtenez l'instance du router via l'injection
  const router = inject(Router);

  console.log('Route path:', route.routeConfig?.path);
  console.log('Requested URL:', state.url);


  // Vérifiez si les identifiants sont stockés dans le localStorage
  const authCredentials = localStorage.getItem('authCredentials');
  const isAuthenticated = authCredentials != null && authCredentials.length > 0;


  console.log('AuthCredentials present ?:', authCredentials);
  console.log('Boolean auth:', isAuthenticated);


  if (isAuthenticated) {
    return of(true);
  } else {
    router.navigate(['/login']); // Rediriger vers la page de connexion si non authentifié
    return of(false);
  }
};
