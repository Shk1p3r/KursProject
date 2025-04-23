import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Instructor } from '../../interfaces/instructor';
import { Category } from '../../interfaces/category';
import { InstructorService } from '../../services/instructor.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-instructor-list',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './instructor-list.component.html',
  styleUrl: './instructor-list.component.css'
})
export class InstructorListComponent {
  instructors: Instructor[] = [];
  filterFio: string = '';
  filterSeniority: number | null = null;
  constructor(private instructorService: InstructorService,  private router: Router) {}

  ngOnInit() {
    this.loadInstructors();
  }

  loadInstructors() {
    this.filterFio = '';
    this.filterSeniority = null;

    this.instructorService.searchInstructors(this.filterFio, this.filterSeniority)
      .subscribe((instructors) => {
        this.instructors = instructors;
      });
  }
  deleteInstructor(id: number) {
    this.instructorService.deleteInstructor(id).subscribe(() => {
      this.instructors = this.instructors.filter((instructor) => instructor.id !== id);
    });
  }
  searchInstructors() {
    this.instructorService.searchInstructors(this.filterFio, this.filterSeniority)
      .subscribe((instructors) => {
        this.instructors = instructors;
      });
  }
  editInstructor(id: number) {
    this.router.navigate(['/instructors', id]);
  }
}
