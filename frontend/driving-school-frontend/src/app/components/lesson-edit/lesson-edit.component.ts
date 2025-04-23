import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Lesson } from '../../interfaces/lesson';
import { LessonService } from '../../services/lesson.service';

@Component({
  selector: 'app-lesson-edit',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './lesson-edit.component.html',
  styleUrl: './lesson-edit.component.css'
})
export class LessonEditComponent implements OnInit{
  lesson: Lesson = {
    id: 0,
    startDate: '',
    endDate: '',
    carId: 0,
    studentId: 0,
    instructorId: 0
  };
  isNew = false;

  constructor(
    private lessonService: LessonService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.isNew = this.route.snapshot.url.join('/') === 'lessons/new';
    if (id && !this.isNew) {
      this.loadLesson(+id);
    }
  }

  loadLesson(id: number): void {
    this.lessonService.getLessonById(id).subscribe((lesson) => {
      this.lesson = lesson;
    });
  }

  saveLesson(): void {
    const request = this.isNew
      ? this.lessonService.createLesson(this.lesson)
      : this.lessonService.updateLesson(this.lesson);

    request.subscribe(() => {
      this.router.navigate(['/lessons']);
    });
  }
}
