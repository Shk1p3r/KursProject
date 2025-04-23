import { Lesson } from "./lesson";

export interface Instructor {
  id: number;
  fio: string;
  seniority: number;
  lessons: Lesson[];
  category: string;
}
