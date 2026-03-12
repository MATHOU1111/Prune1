import { Routes } from '@angular/router';
import { FilmList } from './film-list/film-list';
import { FilmDetail } from './film-detail/film-detail';
import { LoginComponent } from './login/login';
import { authGuard } from './guard/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'films', component: FilmList, canActivate: [authGuard] },
  { path: 'films/:id', component: FilmDetail, canActivate: [authGuard] }
];


