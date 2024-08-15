import {Role} from "./role";
import {Entrepot} from "./entrepot";

export interface Utilisateur {
  id: number;
  username: string;
  contact: string;
  email: string;
  password: string;
  role?: Role;
  entrepot?: Entrepot;
}
