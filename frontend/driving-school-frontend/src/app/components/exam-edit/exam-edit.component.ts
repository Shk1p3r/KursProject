import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Exam } from '../../interfaces/exam';
import { Student } from '../../interfaces/student';
import { ExamService } from '../../services/exam.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-exam-edit',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './exam-edit.component.html',
  styleUrl: './exam-edit.component.css'
})
export class ExamEditComponent implements OnInit{
  exam: Exam = {
    id: 0,
    date: '',
    typeOfExams: '',
    result: '',
    studentId: 0

  };
  students: Student[] = [];

  constructor(
    private examService: ExamService,
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.loadStudents();
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.examService.getExamById(+id).subscribe((exam) => {
        this.exam = exam;
        this.exam.date = exam.date.split('T')[0];
      });
    }
  }

  loadStudents() {
    this.http.get<Student[]>('/api/students').subscribe((students) => {
      this.students = students;
    });
  }

  saveExam() {
    const observable: Observable<Exam> = this.exam.id
      ? this.examService.updateExam(this.exam)
      : this.examService.createExam(this.exam);
    observable.subscribe(() => {
      this.router.navigate(['/exams']);
    });
  }
}
