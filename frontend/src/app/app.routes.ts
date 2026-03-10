import { Routes } from '@angular/router';
import { FilmList } from './film-list/film-list';
import { film-detail } from './film-detail/film-detail';

export const routes: Routes = [
  { path: '', redirectTo: '/films', pathMatch: 'full' },
  { path: 'films', component: FilmList }
  { path: 'films/:id', component: fim-detail }
];
