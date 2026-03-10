import { Routes } from '@angular/router';
import { FilmList } from './film-list/film-list';

export const routes: Routes = [
  { path: '', redirectTo: '/films', pathMatch: 'full' },
  { path: 'films', component: FilmList }
];
