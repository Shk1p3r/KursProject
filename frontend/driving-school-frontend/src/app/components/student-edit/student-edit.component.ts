import { Component, OnInit } from '@angular/core';
import { StudentService } from '../../services/student.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Student } from '../../interfaces/student';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Exam } from '../../interfaces/exam';
import { ExamService } from '../../services/exam.service';
import { Lesson } from '../../interfaces/lesson';
import { LessonService } from '../../services/lesson.service';

@Component({
  selector: 'app-student-edit',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './student-edit.component.html',
  styleUrl: './student-edit.component.css',
})
export class StudentEditComponent implements OnInit {
  student: Student = {
    id: 0,
    fio: '',
    dateOfBirth: '',
    phone: '',
    groupNumber: '',
    lessons: [],
    exams: [],
  };
  availableExams: Exam[] = [];
  selectedExam: Exam | null = null;
  availableLessons: Lesson[] = [];
  selectedLesson: Lesson | null = null;
  isNew = false;
  constructor(
    private studentService: StudentService,
    private examService: ExamService,
    private http: HttpClient,
    private lessonService: LessonService,
    private route: ActivatedRoute,
    private router: Router
  ) {}
  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.isNew = this.route.snapshot.url.join('/') === 'students/new';

    if (id && !this.isNew) {
      this.loadStudent(+id);
    }
    this.loadExams();
    this.loadLessons();
  }

  loadStudent(id: number): void {
    this.studentService.getStudentById(id).subscribe((student) => {
      this.student = student;
      this.filterAvailableExams();
    });
  }
  loadExams(): void {
    this.examService.getAllExams().subscribe((exams) => {
      this.availableExams = exams;
      this.filterAvailableExams();
    });
  }
  saveStudent(): void {
    const request = this.isNew
      ? this.studentService.createStudent(this.student)
      : this.studentService.updateStudent(this.student);

    request.subscribe(() => {
      this.router.navigate(['/students']);
    });
  }
  filterAvailableExams(): void {
    this.availableExams = this.availableExams.filter(
      (exam) => !exam.studentId
    );
  }

  addExam(): void {
    if (this.selectedExam) {
      this.student.exams.push(this.selectedExam);
      this.filterAvailableExams();
    }
  }
  loadLessons(): void {
    this.lessonService.getAllLessons().subscribe((lessons) => {
      this.availableLessons = lessons;
      this.filterAvailableLessons();
    });
  }
  
  filterAvailableLessons(): void {
    this.availableLessons = this.availableLessons.filter(
      (lesson) => !lesson.studentId
    );
  }
  
  addLesson(): void {
    if (this.selectedLesson) {
      this.student.lessons.push(this.selectedLesson);
      this.filterAvailableLessons();
    }
  }
  removeExam(examId: number): void {
    this.student.exams = this.student.exams.filter((exam) => exam.id !== examId);
    this.loadExams();
  }
  
  removeLesson(lessonId: number): void {
    this.student.lessons = this.student.lessons.filter((lesson) => lesson.id !== lessonId);
    this.loadLessons();
  }
}
