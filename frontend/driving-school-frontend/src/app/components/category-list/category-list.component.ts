import { Component, OnInit } from '@angular/core';
import { Category } from '../../interfaces/category';
import { CategoryService } from '../../services/category.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-category-list',
  imports: [CommonModule, FormsModule,RouterLink],
  templateUrl: './category-list.component.html',
  styleUrl: './category-list.component.css'
})
export class CategoryListComponent implements OnInit{
  categories: Category[] = [];
  filterName = '';

  constructor(private categoryService: CategoryService, private router: Router, private http: HttpClient) {}
  ngOnInit(): void {
    this.loadCategories();
  }
  loadCategories() {
    this.filterName='';
    this.categoryService.getAllCategories().subscribe(categories => {
      this.categories = categories;
    });
  }
  deleteCategory(name: string) {
    this.categoryService.deleteCategory(name).subscribe(() => {
      this.categories = this.categories.filter((categories) => categories.name !== name);
    });
  }
  getCategoryByName() {
    this.categories = [];
    this.categoryService.getCategoryByName(this.filterName).subscribe((categories) => {
      this.categories = [categories];
      });
  }
  editCategory(name: string) {
    this.router.navigate(['/categories', name]);
  }
  hasRole(role: string): boolean {
    const userRole = localStorage.getItem('role');
    return userRole === role;
  }
  logout() {
    this.http.post('/logout', {}, { withCredentials: true }).subscribe(() => {
      localStorage.removeItem('role'); 
      this.router.navigate(['/login']);
    });
  }
  goHome(): void {
    this.router.navigate(['/home']);
  }
}
