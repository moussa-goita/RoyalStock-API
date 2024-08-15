export interface Notification {
  id: number;
  contenu: string;
  dateNotif: Date;
  read: boolean;
  utilisateur: {
    id: number;
    username: string;
  };
}
