import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Produit } from '../models/produit';

@Injectable({
  providedIn: 'root'
})
export class ProduitService {
  private apiUrl = 'http://localhost:8080/api/produits';

  constructor(private http: HttpClient) { }

  getProduits(): Observable<Produit[]> {
    return this.http.get<Produit[]>(this.apiUrl);
  }

  getProduitById(id: number): Observable<Produit> {
    return this.http.get<Produit>(`${this.apiUrl}/${id}`);
  }

  // createProduit(produit: Produit): Observable<Produit> {
  //   return this.http.post<Produit>(this.apiUrl, produit);
  // }

  createProduit(produit: Produit, email: string): Observable<Produit> {
    return this.http.post<Produit>(`${this.apiUrl}/create?email=${email}`, produit);
  }

  getProduitsByEntrepot(entrepotId: number): Observable<Produit[]> {
    return this.http.get<Produit[]>(`${this.apiUrl}/entrepot/${entrepotId}`);
  }
  
  updateProduit(id: number, produit: Produit): Observable<Produit> {
    return this.http.put<Produit>(`${this.apiUrl}/${id}`, produit);
  }

  deleteProduit(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
