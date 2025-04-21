import { Car } from "./car";
import { Instructor } from "./instructor";
import { Student } from "./student";

export interface Lesson {
    id: number;
    startDate: string;
    endDate: string;
    student: Student;
    instructor: Instructor;
    car: Car;
}
