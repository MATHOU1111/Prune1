// film.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookmarkService {
  private apiUrl = 'http://localhost:8080/api/bookmarks';

  constructor(private http: HttpClient) { }

  getBookmarks(): Observable<any> {
    return this.http.get(this.apiUrl);
  }
}
