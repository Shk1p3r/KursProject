import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class Auth implements CanActivate {
  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRoles: string[] = route.data['roles'] || [];
    const role = localStorage.getItem('role');

    if (!role) {
      this.router.navigate(['/login']);
      return false;
    }

    if (expectedRoles.length === 0 || expectedRoles.includes(role.toUpperCase())) {
      return true;
    }

    this.router.navigate(['/home']);
    return false;
  }
}