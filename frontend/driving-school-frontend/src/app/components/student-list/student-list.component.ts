import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Student } from '../../interfaces/student';
import { StudentService } from '../../services/student.service';

@Component({
  selector: 'app-student-list',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.css'
})
export class StudentListComponent {
  students: Student[] = [];
  filterDateOfBirth: string = '';
  filterFio: string = '';
  filterPhone: string = '';
  filterGroupNumber: string = '';
  constructor(private studentService: StudentService, private router: Router) {}

  ngOnInit() {
    this.loadStudents();
  }

  loadStudents() {
    this.filterDateOfBirth = '';
    this.filterFio = '';
    this.filterPhone = '';
    this.filterGroupNumber = '';
    this.studentService.searchStudents(this.filterDateOfBirth, this.filterFio, this.filterPhone, this.filterGroupNumber)
      .subscribe((students) => {
        this.students = students;
      });
  }
  deleteStudent(id: number) {
    this.studentService.deleteStudent(id).subscribe(() => {
      this.students = this.students.filter((student) => student.id !== id);
    });
  }
  searchStudents() {
    this.studentService.searchStudents(this.filterDateOfBirth, this.filterFio, this.filterPhone, this.filterGroupNumber)
      .subscribe((students) => {
        this.students = students;
      });
  }
  editStudent(id: number) {
    this.router.navigate(['/students', id]);
  }
}
