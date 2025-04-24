import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [CommonModule, RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  constructor(private router: Router, private http: HttpClient) {}
  getRole(): string {
    const user = localStorage.getItem('role');
    return user ? user.toUpperCase() : '';
  }

  hasRole(...roles: string[]): boolean {
    return roles.includes(this.getRole());
  }
  logout() {
    this.http.post('/logout', {}, { withCredentials: true }).subscribe(() => {
      localStorage.removeItem('role'); 
      this.router.navigate(['/login']);
    });
  }
}
