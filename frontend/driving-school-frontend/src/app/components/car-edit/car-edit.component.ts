import { Component, OnInit } from '@angular/core';
import { Car } from '../../interfaces/car';
import { CarService } from '../../services/car.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LessonService } from '../../services/lesson.service';
import { Lesson } from '../../interfaces/lesson';

@Component({
  selector: 'app-car-edit',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './car-edit.component.html',
  styleUrl: './car-edit.component.css'
})
export class CarEditComponent implements OnInit {
  car: Car = {
    id: 0,
    mark: '',
    model: '',
    yearOfProduction: 0,
    licensePlateNumber: '',
    lessons: []
  };
  availableLessons: Lesson[] = [];
  selectedLesson: Lesson | null = null;
  isNew = false;

  constructor(
    private carService: CarService,
    private lessonService: LessonService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.isNew = this.route.snapshot.url.join('/') === 'cars/new';
    this.loadLessons();
    if (id && !this.isNew) {
      this.loadCar(+id);
    }
  }

  loadCar(id: number): void {
    this.carService.getCarById(id).subscribe((car) => {
      this.car = car;
      this.filterAvailableLessons();
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

  loadLessons(): void {
    this.lessonService.getAllLessons().subscribe((lessons) => {
      this.availableLessons = lessons;
      this.filterAvailableLessons();
    });
  }

  addLesson(): void {
    if (this.selectedLesson) {
      this.car.lessons.push(this.selectedLesson);
      this.availableLessons = this.availableLessons.filter(l => l.id !== this.selectedLesson!.id);
      this.selectedLesson = null;
    }
  }

  removeLesson(lessonId: number): void {
    const lesson = this.car.lessons.find(l => l.id === lessonId);
    if (lesson) {
      this.car.lessons = this.car.lessons.filter(l => l.id !== lessonId);
      if (!lesson.carId) {
        this.availableLessons.push(lesson);
      }
    }
  }

  filterAvailableLessons(): void {
    const usedLessonIds = this.car.lessons.map(l => l.id);
    this.availableLessons = this.availableLessons.filter(
      lesson => !usedLessonIds.includes(lesson.id) && !lesson.carId
    );
  }
}

