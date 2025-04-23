import { Exam } from "./exam";
import { Lesson } from "./lesson";

export interface Student {
  id: number;
  fio: string;
  dateOfBirth: string;
  phone: string;
  groupNumber: string;
  lessons: Lesson[];
  exams: Exam[];
}
