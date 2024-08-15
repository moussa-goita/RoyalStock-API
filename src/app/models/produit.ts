import { Categorie } from './categorie';

export class Produit {
  id!: number;
  productName!: string;
  description!: string;
  quantity!: number;
  createBy!: number;
  //categories_id: number;
  categorie!: Categorie ;
}
