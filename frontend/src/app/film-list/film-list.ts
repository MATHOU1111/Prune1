import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { BookmarkService, Bookmark } from '../Service/film.service';
import { AuthService } from '../Service/auth.service';

@Component({
  selector: 'app-film-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './film-list.html',
  styleUrl: './film-list.scss',
})
export class FilmList implements OnInit {
  movies: Bookmark[] = [];
  isLoading = true;
  errorMessage = '';

  constructor(
    private bookmarkService: BookmarkService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.bookmarkService.getBookmarks().subscribe({
      next: (data) => {
        this.movies = data;
        this.isLoading = false;
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors du chargement des bookmarks.';
        this.isLoading = false;
        console.error(err);
      }
    });
  }

  goToDetail(id: number): void {
    this.router.navigate(['/films', id]);
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  get username(): string | null {
    return this.authService.getUsername();
  }
}



