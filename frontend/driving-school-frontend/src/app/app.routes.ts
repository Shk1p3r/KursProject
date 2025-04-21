import { Routes } from '@angular/router';
import { CarListComponent } from './components/car-list/car-list.component';
import { CarEditComponent } from './components/car-edit/car-edit.component';
import { CategoryListComponent } from './components/category-list/category-list.component';
import { CategoryEditComponent } from './components/category-edit/category-edit.component';
import { ExamListComponent } from './components/exam-list/exam-list.component';
import { ExamEditComponent } from './components/exam-edit/exam-edit.component';
import { StudentListComponent } from './components/student-list/student-list.component';
import { StudentEditComponent } from './components/student-edit/student-edit.component';
import { LessonListComponent } from './components/lesson-list/lesson-list.component';
import { LessonEditComponent } from './components/lesson-edit/lesson-edit.component';
import { InstructorListComponent } from './components/instructor-list/instructor-list.component';
import { InstructorEditComponent } from './components/instructor-edit/instructor-edit.component';

export const routes: Routes = [
    { path: 'cars', component: CarListComponent },
    { path: 'cars/new', component: CarEditComponent },
    { path: 'cars/:id', component: CarEditComponent },
    { path: 'categories', component: CategoryListComponent },
    { path: 'categories/new', component: CategoryEditComponent },
    { path: 'categories/:name', component: CategoryEditComponent },
    { path: 'exams', component: ExamListComponent },
    { path: 'exams/new', component: ExamEditComponent },
    { path: 'exams/:id', component: ExamEditComponent },
    { path: 'students', component: StudentListComponent },
    { path: 'students/new', component: StudentEditComponent },
    { path: 'students/:id', component: StudentEditComponent },
    { path: 'lessons', component: LessonListComponent },
    { path: 'lessons/new', component: LessonEditComponent },
    { path: 'lessons/:id', component: LessonEditComponent },
    { path: 'instructors', component: InstructorListComponent },
    { path: 'instructors/new', component: InstructorEditComponent },
    { path: 'instructors/:id', component: InstructorEditComponent },
    { path: '', redirectTo: 'cars', pathMatch: 'full' }
];
