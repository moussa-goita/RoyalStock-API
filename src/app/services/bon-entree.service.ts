import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BonEntree } from '../models/bon-entree';


@Injectable({
  providedIn: 'root'
})
export class BonEntreeService {
  private baseUrl = 'http://localhost:8080/api/bon-entrees';

  constructor(private http: HttpClient) { }

  getBonEntreesByEntrepots(entrepotId: number): Observable<BonEntree[]> {
    return this.http.get<BonEntree[]>(`${this.baseUrl}/entrepot/${entrepotId}`);
  }
  

  getBonEntreeById(id: number): Observable<BonEntree> {
    return this.http.get<BonEntree>(`${this.baseUrl}/${id}`);
  }

  createBonEntree(bonEntree: BonEntree, email: string): Observable<BonEntree> {
    return this.http.post<BonEntree>(`${this.baseUrl}?email=${email}`, bonEntree);
  }

  updateBonEntree(id: number, bonEntree: BonEntree): Observable<BonEntree> {
    return this.http.put<BonEntree>(`${this.baseUrl}/${id}`, bonEntree);
  }

  validateBonEntree(id: number): Observable<BonEntree> {
    return this.http.post<BonEntree>(`${this.baseUrl}/valider/${id}`, {});
  }
  
  deleteBonEntree(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
