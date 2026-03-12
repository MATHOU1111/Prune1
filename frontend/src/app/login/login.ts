import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../Service/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class LoginComponent {
  username = '';
  password = '';
  errorMessage = '';
  isLoading = false;
  isRegisterMode = false;

  constructor(private authService: AuthService, private router: Router) {}

  toggleMode(): void {
    this.isRegisterMode = !this.isRegisterMode;
    this.errorMessage = '';
  }

  submit(): void {
    if (!this.username || !this.password) {
      this.errorMessage = 'Veuillez remplir tous les champs.';
      return;
    }
    this.isLoading = true;
    this.errorMessage = '';

    const request = { username: this.username, password: this.password };
    const action$ = this.isRegisterMode
      ? this.authService.register(request)
      : this.authService.login(request);

    action$.subscribe({
      next: () => this.router.navigate(['/films']),
      error: (err) => {
        this.isLoading = false;
        this.errorMessage = err.error?.message ?? 'Identifiants incorrects.';
      }
    });
  }
}

