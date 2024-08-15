import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private apiUrl = 'http://localhost:8080/api/dashboard';  // Ajustez l'URL selon votre backend

  constructor(private http: HttpClient) { }

  getStockInfo(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/stock-info`);
  }
}
