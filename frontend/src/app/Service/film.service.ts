import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Bookmark {
  id: number;
  title: string;
  url: string;
  description?: string;
  genre?: string;
  posterUrl?: string;
  year?: number;
  rating?: number;
  createdAt: string;
  updatedAt: string;
}

export interface CreateBookmarkRequest {
  title: string;
  url?: string;
  description?: string;
  genre?: string;
  posterUrl?: string;
  year?: number;
  rating?: number;
}

@Injectable({ providedIn: 'root' })
export class BookmarkService {
  private apiUrl = 'http://localhost:8080/api/bookmarks';

  constructor(private http: HttpClient) {}

  getBookmarks(): Observable<Bookmark[]> {
    return this.http.get<Bookmark[]>(this.apiUrl);
  }

  getBookmarkById(id: number): Observable<Bookmark> {
    return this.http.get<Bookmark>(`${this.apiUrl}/${id}`);
  }

  createBookmark(data: CreateBookmarkRequest): Observable<Bookmark> {
    return this.http.post<Bookmark>(this.apiUrl, data);
  }

  updateBookmark(id: number, data: CreateBookmarkRequest): Observable<Bookmark> {
    return this.http.put<Bookmark>(`${this.apiUrl}/${id}`, data);
  }

  deleteBookmark(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
