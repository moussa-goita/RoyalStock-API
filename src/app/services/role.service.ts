import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Role } from '../models/role';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  private baseUrl = 'http://localhost:8080/api/roles';

  constructor(private http: HttpClient) { }

  getRoles(): Observable<Role[]> {
    return this.http.get<Role[]>(`${this.baseUrl}`);
  }

  getRoleById(id: number): Observable<Role> {
    return this.http.get<Role>(`${this.baseUrl}/${id}`);
  }

  createRole(role: Role): Observable<Role> {
    return this.http.post<Role>(`${this.baseUrl}`, role);
  }

  updateRole(id: number, role: Role): Observable<Role> {
    return this.http.put<Role>(`${this.baseUrl}/${id}`, role);
  }

  deleteRole(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
