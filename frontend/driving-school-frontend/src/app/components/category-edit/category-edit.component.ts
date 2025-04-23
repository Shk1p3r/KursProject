import { Component } from '@angular/core';
import { Category } from '../../interfaces/category';
import { CategoryService } from '../../services/category.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Instructor } from '../../interfaces/instructor';
import { InstructorService } from '../../services/instructor.service';

@Component({
  selector: 'app-category-edit',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './category-edit.component.html',
  styleUrl: './category-edit.component.css'
})
export class CategoryEditComponent {
  category: Category = {
    name: '',
    instructors: []
  };
  allInstructors: Instructor[] = [];
  selectedInstructor: Instructor | null = null;
  availableInstructors: Instructor[] = [];
  isNew=false;
  constructor(
    private categoryService: CategoryService,
    private instructorService: InstructorService,
    private route: ActivatedRoute,
    private router: Router
  ) {}
  ngOnInit(): void {
    const name = this.route.snapshot.paramMap.get('name');
    this.isNew = this.route.snapshot.url.join('/') === 'categories/new';
    
    if (name && !this.isNew) {
      this.loadCategory(name);
    }
    this.loadInstructors();
  }

  loadCategory(name: string): void {
    this.categoryService.getCategoryByName(name).subscribe((category) => {
      this.category = category;
      this.loadInstructors();
    });
    
  }

  saveCategory(): void {
    const request = this.isNew
      ? this.categoryService.createCategory(this.category)
      : this.categoryService.updateCategory(this.category);

    request.subscribe(() => {
      this.router.navigate(['/categories']);
    });
  }
  loadInstructors(): void {
    this.instructorService.getAllInstructors().subscribe((data) => {
      this.allInstructors = data;
      this.filterAvailableInstructors();
    });
  }
  filterAvailableInstructors(): void {
    const selectedIds = this.category.instructors.map(i => i.id);
  
    this.availableInstructors = this.allInstructors.filter(instructor =>
      (!instructor.category || instructor.category.trim() === '') && !selectedIds.includes(instructor.id)
    );
  }

  addInstructor(): void {
    if (this.selectedInstructor) {
      this.category.instructors.push(this.selectedInstructor);
      this.selectedInstructor = null;
      this.filterAvailableInstructors();
    }
  }
  removeInstructor(instructorId: number): void {
    this.category.instructors = this.category.instructors.filter((instructor) => instructor.id !== instructorId);
    this.filterAvailableInstructors();
  }
}
