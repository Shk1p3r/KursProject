import { Component, OnInit } from '@angular/core';
import { Car } from '../../interfaces/car';
import { CarService } from '../../services/car.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-car-edit',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './car-edit.component.html',
  styleUrl: './car-edit.component.css'
})
export class CarEditComponent implements OnInit{
  car: Car = {
    id: 0,
    mark: '',
    model: '',
    yearOfProduction: new Date().getFullYear(),
    licensePlateNumber: ''
  };
  isNew = false;
  constructor(
    private carService: CarService,
    private route: ActivatedRoute,
    private router: Router
  ) {}
  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.isNew = this.route.snapshot.url.join('/') === 'cars/new';

    if (id && !this.isNew) {
      this.loadCar(+id);
    }
  }

  loadCar(id: number): void {
    this.carService.getCarById(id).subscribe((car) => {
      this.car = car;
    });
  }

  saveCar(): void {
    const request = this.isNew
      ? this.carService.createCar(this.car)
      : this.carService.updateCar(this.car);

    request.subscribe(() => {
      this.router.navigate(['/cars']);
    });
  }
}
