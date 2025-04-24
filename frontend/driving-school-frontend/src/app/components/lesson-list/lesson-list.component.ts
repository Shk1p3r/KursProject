import { Component, OnInit } from '@angular/core';
import { Lesson } from '../../interfaces/lesson';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { LessonService } from '../../services/lesson.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-lesson-list',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './lesson-list.component.html',
  styleUrl: './lesson-list.component.css',
})
export class LessonListComponent implements OnInit {
  lessons: Lesson[] = [];
  constructor(
    private lessonService: LessonService,
    private router: Router,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.loadLessons();
  }

  loadLessons(): void {
    this.lessonService.getAllLessons().subscribe((lessons) => {
      this.lessons = lessons;
    });
  }

  deleteLesson(id: number): void {
    this.lessonService.deleteLesson(id).subscribe(() => {
      this.loadLessons();
    });
  }

  editLesson(id: number): void {
    this.router.navigate([`/lessons`, id]);
  }
  hasRole(role: string): boolean {
    const userRole = localStorage.getItem('role');
    return userRole === role;
  }
  logout() {
    this.http
      .post('/logout', {}, { withCredentials: true })
      .subscribe(() => {
        localStorage.removeItem('role');
        this.router.navigate(['/login']);
      });
  }
  goHome(): void {
    this.router.navigate(['/home']);
  }
}
