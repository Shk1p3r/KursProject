import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username = '';
  password = '';

  constructor(private http: HttpClient, private router: Router) {}

  login(): void {
    const body = {
      username: this.username,
      password: this.password
    };
  
    this.http.post('/api/login', body, {
      headers: { 'Content-Type': 'application/json' },
      withCredentials: true,
      observe: 'response'
    }).subscribe({
      next: (res) => {
        if (res.status === 302 || res.status === 200) {
          this.fetchRole();
        } else {
          alert('Неизвестный ответ от сервера');
        }
      },
      error: () => alert('Неверный логин или пароль')
    });
  }

  fetchRole(): void {
    this.http.get<{ role: string }>('/api/me', { withCredentials: true }).subscribe(res => {
      localStorage.setItem('role', res.role);
      this.router.navigate(['/home']);
    });
  }
  logout() {
    this.http.post('/logout', {}, { withCredentials: true }).subscribe(() => {
      localStorage.removeItem('role');
      this.router.navigate(['/login']);
    });
  }
}
