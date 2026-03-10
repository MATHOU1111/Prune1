import { Component, OnInit } from '@angular/core';
import { BookmarkService } from '../Service/film.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-film-list',
  imports: [CommonModule],
  standalone: true,
  templateUrl: './film-list.html',
  styleUrl: './film-list.scss',
})
export class FilmList implements OnInit {
  Movies : any[] = [];

  constructor(private bookmarkService: BookmarkService) { }

  ngOnInit(): void {
      this.bookmarkService.getBookmarks().subscribe(
        (data) => {
          this.Movies = data;
          console.log('Api GET RESULT', this.Movies);
        },
        (error) => {
          console.error('Erreur lors de la récupération des bookmarks:', error);
        }
      );
  }
 }


