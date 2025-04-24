import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Instructor } from '../../interfaces/instructor';
import { Category } from '../../interfaces/category';
import { InstructorService } from '../../services/instructor.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Lesson } from '../../interfaces/lesson';

@Component({
  selector: 'app-instructor-edit',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './instructor-edit.component.html',
  styleUrl: './instructor-edit.component.css',
})
export class InstructorEditComponent implements OnInit {
  instructor: Instructor = {
    id: 0,
    fio: '',
    seniority: null as any,
    lessons: [],
    category: '',
  };
  categories: Category[] = [];
  allLessons: Lesson[] = [];
  availableLessons: Lesson[] = [];
  selectedLesson: Lesson | null = null;
  constructor(
    private instuctorService: InstructorService,
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.loadCategories();
    this.loadLessons();
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.instuctorService.getInstructorById(+id).subscribe((instructor) => {
        this.instructor = instructor;
        this.filterAvailableLessons();
      });
    }
  }
  loadLessons() {
    this.http.get<Lesson[]>('/api/lessons').subscribe((lessons) => {
      this.allLessons = lessons;
      this.filterAvailableLessons();
    });
  }
  filterAvailableLessons() {
    const usedLessonIds = this.instructor.lessons.map(l => l.id);
    this.availableLessons = this.allLessons.filter(lesson =>
       !usedLessonIds.includes(lesson.id) && !lesson.instructorId
    );
  }
  addLesson() {
    if (this.selectedLesson) {
      this.instructor.lessons.push(this.selectedLesson);
      this.availableLessons = this.availableLessons.filter(l => l.id !== this.selectedLesson!.id);
      this.selectedLesson = null;
    }
  }
  
  removeLesson(id: number) {
    this.instructor.lessons = this.instructor.lessons.filter(l => l.id !== id);
    this.filterAvailableLessons();
  }
  loadCategories() {
    this.http.get<Category[]>('/api/categories').subscribe((categories) => {
      this.categories = categories;
    });
  }
  

  saveInstructor() {
    const observable: Observable<Instructor> = this.instructor.id
      ? this.instuctorService.updateInstructor(this.instructor)
      : this.instuctorService.createInstructor(this.instructor);
    observable.subscribe(() => {
      this.router.navigate(['/instructors']);
    });
  }
}
