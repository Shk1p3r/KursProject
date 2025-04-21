import { Component } from '@angular/core';
import { Category } from '../../interfaces/category';
import { CategoryService } from '../../services/category.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-category-edit',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './category-edit.component.html',
  styleUrl: './category-edit.component.css'
})
export class CategoryEditComponent {
  category: Category = {
    name: ''
  };
  isNew=false;
  constructor(
    private categoryService: CategoryService,
    private route: ActivatedRoute,
    private router: Router
  ) {}
  ngOnInit(): void {
    const name = this.route.snapshot.paramMap.get('name');
    this.isNew = this.route.snapshot.url.join('/') === 'categories/new';

    if (name && !this.isNew) {
      this.loadCategory(name);
    }
  }

  loadCategory(name: string): void {
    this.categoryService.getCategoryByName(name).subscribe((category) => {
      this.category = category;
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
}
