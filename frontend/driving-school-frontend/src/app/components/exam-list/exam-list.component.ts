import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Exam } from '../../interfaces/exam';
import { ExamService } from '../../services/exam.service';

@Component({
  selector: 'app-exam-list',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './exam-list.component.html',
  styleUrl: './exam-list.component.css'
})
export class ExamListComponent {
  exams: Exam[] = [];
  filterDate = '';
  filterType = '';
  filterResult = '';
  constructor(private examService: ExamService, private router: Router) {}
  ngOnInit() {
    this.loadExams();
  }
  loadExams() {
    this.filterDate = '';
    this.filterType = '';
    this.filterResult = '';
    this.examService.getAllExams().subscribe((exams) => {
      this.exams = exams;
    });
  }
  deleteExam(id: number) {
    this.examService.deleteExam(id).subscribe(() => {
      this.exams = this.exams.filter((exam) => exam.id !== id);
    });
  }
  searchExams() {
    this.examService
      .searchExams(this.filterDate, this.filterType,  this.filterResult)
      .subscribe((exams) => {this.exams = exams;
      });
  }
  editExam(id: number) {
    this.router.navigate(['/exams', id]);
  }
}
