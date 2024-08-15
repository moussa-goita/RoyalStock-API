import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Notification } from '../models/notification';
//import { LocalNotifications } from '@ionic-native/local-notifications/ngx';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  /*constructor(private localNotifications: LocalNotifications) {}

  scheduleNotification(title: string, text: string) {
    this.localNotifications.schedule({
      id: 1,
      title: title,
      text: text,
      trigger: { at: new Date(new Date().getTime() + 3600) }, // Notification dans 1 heure
      led: 'FF0000',
      sound: null
    });
  }*/

  /*private apiUrl = 'http://localhost:8080/api/notifications';

  constructor(private http: HttpClient) {}

  getNotifications(): Observable<Notification[]> {
    return this.http.get<Notification[]>(this.apiUrl);
  }

  getUnreadNotifications(): Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.apiUrl}/unread`);
  }

  markAsRead(id: number): Observable<void> {
    return this.http.patch<void>(`${this.apiUrl}/${id}/read`, {});
  }

  getNotificationById(id: number): Observable<Notification> {
    return this.http.get<Notification>(`${this.apiUrl}/${id}`);
  }

  createNotification(notification: Notification): Observable<Notification> {
    return this.http.post<Notification>(this.apiUrl, notification);
  }

  updateNotification(id: number, notification: Notification): Observable<Notification> {
    return this.http.put<Notification>(`${this.apiUrl}/${id}`, notification);
  }

  deleteNotification(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }*/
}
