import { Entrepot } from "./entrepot";

export interface CurrentUser {
    id: number;
    username: string;
    email: string;
    role: string;
    token: string;
    entrepot?: Entrepot; 
  }