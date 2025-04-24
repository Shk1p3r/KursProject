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
import { Auth } from './auth';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'home', component: HomeComponent, canActivate: [Auth] },
    { path: 'cars', component: CarListComponent, canActivate: [Auth], data: { roles: ['DIRECTOR', 'INSTRUCTOR'] }},
    { path: 'cars/new', component: CarEditComponent, canActivate: [Auth], data: { roles: ['DIRECTOR', 'INSTRUCTOR'] }},
    { path: 'cars/:id', component: CarEditComponent, canActivate: [Auth], data: { roles: ['DIRECTOR', 'INSTRUCTOR'] }},
  
    { path: 'categories', component: CategoryListComponent, canActivate: [Auth], data: { roles: ['DIRECTOR'] }},
    { path: 'categories/new', component: CategoryEditComponent, canActivate: [Auth], data: { roles: ['DIRECTOR'] }},
    { path: 'categories/:name', component: CategoryEditComponent, canActivate: [Auth], data: { roles: ['DIRECTOR'] }},
  
    { path: 'exams', component: ExamListComponent, canActivate: [Auth], data: { roles: ['DIRECTOR'] }},
    { path: 'exams/new', component: ExamEditComponent, canActivate: [Auth], data: { roles: ['DIRECTOR'] }},
    { path: 'exams/:id', component: ExamEditComponent, canActivate: [Auth], data: { roles: ['DIRECTOR'] }},
  
    { path: 'students', component: StudentListComponent, canActivate: [Auth], data: { roles: ['DIRECTOR', 'INSTRUCTOR', 'STUDENT'] }},
    { path: 'students/new', component: StudentEditComponent, canActivate: [Auth], data: { roles: ['DIRECTOR', 'INSTRUCTOR'] }},
    { path: 'students/:id', component: StudentEditComponent, canActivate: [Auth], data: { roles: ['DIRECTOR', 'INSTRUCTOR'] }},
  
    { path: 'lessons', component: LessonListComponent, canActivate: [Auth], data: { roles: ['DIRECTOR'] }},
    { path: 'lessons/new', component: LessonEditComponent, canActivate: [Auth], data: { roles: ['DIRECTOR'] }},
    { path: 'lessons/:id', component: LessonEditComponent, canActivate: [Auth], data: { roles: ['DIRECTOR'] }},
  
    { path: 'instructors', component: InstructorListComponent, canActivate: [Auth], data: { roles: ['DIRECTOR', 'INSTRUCTOR'] }},
    { path: 'instructors/new', component: InstructorEditComponent, canActivate: [Auth], data: { roles: ['DIRECTOR', 'INSTRUCTOR'] }},
    { path: 'instructors/:id', component: InstructorEditComponent, canActivate: [Auth], data: { roles: ['DIRECTOR', 'INSTRUCTOR'] }},
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: '**', redirectTo: 'login' }
];
