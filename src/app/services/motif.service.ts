import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Motif } from '../models/motif';

@Injectable({
  providedIn: 'root'
})
export class MotifService {
  private baseUrl = 'http://localhost:8080/api/motif';

  constructor(private http: HttpClient) { }

  getMotifs(): Observable<Motif[]> {
    return this.http.get<Motif[]>(`${this.baseUrl}`);
  }


  getMotifById(id: number): Observable<Motif> {
    return this.http.get<Motif>(`${this.baseUrl}/${id}`);
  }

  getMotifsForCurrentUser(email: string): Observable<Motif[]> {
    return this.http.get<Motif[]>(`${this.baseUrl}/current?email=${email}`);
  }

  createMotif(name: string, email: string): Observable<Motif> {
    return this.http.post<Motif>(`${this.baseUrl}/create?email=${email}`, { name });
  }

  updateMotif(id: number, motif: Motif): Observable<Motif> {
    return this.http.put<Motif>(`${this.baseUrl}/${id}`, motif);
  }

  deleteMotif(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
