import { Student } from './student';

export interface Exam {
  id: number;
  typeOfExams: string;
  result: string;
  date: string;
  student: Student;
}
