import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';

export const loginGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  // Exemple d'utilisation des paramètres `route` et `state`
  console.log('Route path:', route.routeConfig?.path);
  console.log('Requested URL:', state.url);

  // Vérifiez si l'utilisateur est authentifié
  const authCredentials = localStorage.getItem('authCredentials');
  const isAuthenticated = authCredentials != null && authCredentials.length > 0;

  console.log('AuthCredentials present ?:', isAuthenticated);
  console.log('Boolean auth:', isAuthenticated);

  if (isAuthenticated) {
    // Redirige vers la page 'clientele' si l'utilisateur est déjà connecté
    router.navigate(['/clientele']);
    return false; // Bloque l'accès à la page de connexion
  }

  return true; // Permet l'accès à la page de connexion
};
