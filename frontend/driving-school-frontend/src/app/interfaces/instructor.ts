import { Category } from "./category";

export interface Instructor {
  id: number;
  fio: string;
  phone: string;
  seniority: number;
  category: Category;
}
