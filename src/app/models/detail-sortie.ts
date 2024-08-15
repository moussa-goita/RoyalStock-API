import {Produit} from "./produit";
import {Categorie} from "./categorie";
import {BonSortie} from "./bon-sortie";

export interface DetailSortie {
  id: number;
  quantity: number;
  prix: number;
  bonSortie: BonSortie;
  produit: Produit;
}
