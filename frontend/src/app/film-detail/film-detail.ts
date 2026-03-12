import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { BookmarkService, Bookmark } from '../Service/film.service';

@Component({
  selector: 'app-film-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './film-detail.html',
  styleUrl: './film-detail.scss',
})
export class FilmDetail implements OnInit {
  movie: Bookmark | null = null;
  isLoading = true;
  errorMessage = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private bookmarkService: BookmarkService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!id) {
      this.router.navigate(['/films']);
      return;
    }
    this.bookmarkService.getBookmarkById(id).subscribe({
      next: (data) => {
        this.movie = data;
        this.isLoading = false;
      },
      error: () => {
        this.errorMessage = 'Bookmark introuvable.';
        this.isLoading = false;
      }
    });
  }

  goBack(): void {
    this.router.navigate(['/films']);
  }
}


