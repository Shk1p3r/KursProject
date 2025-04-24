import { Component, OnInit } from '@angular/core';
import { Car } from '../../interfaces/car';
import { CarService } from '../../services/car.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-car-list',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './car-list.component.html',
  styleUrl: './car-list.component.css'
})
export class CarListComponent implements OnInit{
  cars: Car[] = [];
  filterMark = '';
  filterModel = '';
  filterYear: number | null = null;
  filterPlate = '';
  constructor(private carService: CarService, private router: Router, private http: HttpClient) {}
  ngOnInit() {
    this.loadCars();
  }
  loadCars() {
    this.filterMark = '';
    this.filterModel = '';
    this.filterYear = null;
    this.filterPlate = '';
    this.carService.getAllCars().subscribe((cars) => {
      this.cars = cars;
    });
  }
  deleteCar(id: number) {
    this.carService.deleteCar(id).subscribe(() => {
      this.cars = this.cars.filter((car) => car.id !== id);
    });
  }
  searchCars() {
    this.carService
      .searchCars(this.filterMark, this.filterModel, this.filterYear!, this.filterPlate)
      .subscribe((cars) => {this.cars = cars;
      });
  }
  editCar(id: number) {
    this.router.navigate(['/cars', id]);
  }
  hasRole(role: string): boolean {
    const userRole = localStorage.getItem('role');
    return userRole === role;
  }
  logout() {
    this.http.post('http://localhost:8080/logout', {}, { withCredentials: true }).subscribe(() => {
      localStorage.removeItem('role');
      this.router.navigate(['/login']);
    });
  }
  goHome(): void {
    this.router.navigate(['/home']);
  }
}
