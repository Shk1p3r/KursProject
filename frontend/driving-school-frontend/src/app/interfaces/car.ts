import { Lesson } from "./lesson";

export interface Car {
    id:number;
    mark: string;
    model: string;
    yearOfProduction: number;
    licensePlateNumber: string;
    lessons: Lesson[];
}
