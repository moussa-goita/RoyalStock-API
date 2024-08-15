import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DetailEntree } from '../models/detail-entree';

@Injectable({
  providedIn: 'root'
})
export class DetailEntreeService {
  private apiUrl = 'http://localhost:8080/api/details-entrees';

  constructor(private http: HttpClient) { }

  getDetailEntrees(): Observable<DetailEntree[]> {
    return this.http.get<DetailEntree[]>(this.apiUrl);
  }

  getDetailEntreeById(id: number): Observable<DetailEntree> {
    return this.http.get<DetailEntree>(`${this.apiUrl}/${id}`);
  }

  createDetailEntree(detailEntree: DetailEntree): Observable<DetailEntree> {
    return this.http.post<DetailEntree>(this.apiUrl, detailEntree);
  }

  updateDetailEntree(id: number, detailEntree: DetailEntree): Observable<DetailEntree> {
    return this.http.put<DetailEntree>(`${this.apiUrl}/${id}`, detailEntree);
  }

  deleteDetailEntree(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
