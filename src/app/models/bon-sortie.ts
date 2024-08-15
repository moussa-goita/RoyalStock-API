import { DetailSortie } from "./detail-sortie";
import { Entrepot } from "./entrepot";
import { Motif } from "./motif";
import { Utilisateur } from "./utilisateur";

export interface BonSortie {
  id: number;
  motif: Motif;
  dateSortie: Date;
  utilisateur: Utilisateur;
  detailsSorties: DetailSortie[];
  entrepot:Entrepot;
}
