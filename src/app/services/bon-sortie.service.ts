import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BonSortie } from '../models/bon-sortie';

@Injectable({
  providedIn: 'root'
})
export class BonSortieService {
  private apiUrl = 'http://localhost:8080/api/bon-sorties';

  constructor(private http: HttpClient) {}

  getBonSorties(): Observable<BonSortie[]> {
    return this.http.get<BonSortie[]>(this.apiUrl);
  }

  getBonSortieById(id: number): Observable<BonSortie> {
    return this.http.get<BonSortie>(`${this.apiUrl}/${id}`);
  }
  getBonSortiesByEntrepots(entrepotId: number): Observable<BonSortie[]> {
    return this.http.get<BonSortie[]>(`${this.apiUrl}/entrepot/${entrepotId}`);
  }

  createBonSortie(bonSortie: BonSortie, email: string): Observable<BonSortie> {
    return this.http.post<BonSortie>(`${this.apiUrl}?email=${email}`, bonSortie);
  }

  updateBonSortie(id: number, formattedBonSortie: BonSortie): Observable<BonSortie> {
    return this.http.put<BonSortie>(`${this.apiUrl}/${formattedBonSortie.id}`, formattedBonSortie);
  }

  deleteBonSortie(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
