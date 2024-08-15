import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataChartService {
  
  private apiUrl = 'http://localhost:8080/api/stats';

  constructor(private http: HttpClient) { }

  getTrends(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/trends`);
  }

  getStats(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/stats`);
  }

  getSalesByCategory(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/salesByCategory`);
  }
}