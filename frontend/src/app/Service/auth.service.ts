import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

export interface AuthRequest {
  username: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  username: string;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  register(data: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, data).pipe(
      tap(res => this.saveToken(res.token, res.username))
    );
  }

  login(data: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, data).pipe(
      tap(res => this.saveToken(res.token, res.username))
    );
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getUsername(): string | null {
    return localStorage.getItem('username');
  }

  private saveToken(token: string, username: string): void {
    localStorage.setItem('token', token);
    localStorage.setItem('username', username);
  }
}

