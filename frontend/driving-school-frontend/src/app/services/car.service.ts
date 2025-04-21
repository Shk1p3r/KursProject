import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Car } from '../interfaces/car';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarService {
  private apiUrl = `${environment.apiUrl}/cars`;
  constructor(private http: HttpClient) { }
  createCar(car: Car): Observable<Car> {
    return this.http.post<Car>(this.apiUrl, car);
  }

  getCarById(id: number): Observable<Car> {
    return this.http.get<Car>(`${this.apiUrl}/${id}`);
  }

  getAllCars(): Observable<Car[]> {
    return this.http.get<Car[]>(this.apiUrl);
  }

  updateCar(car: Car): Observable<Car> {
    return this.http.put<Car>(this.apiUrl, car);
  }

  deleteCar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  searchCars(mark?: string, model?: string, year?: number, plate?: string): Observable<Car[]> {
    let params: any = {};
    if (mark) params.mark = mark;
    if (model) params.model = model;
    if (year) params.year = year;
    if (plate) params.plate = plate;
    return this.http.get<Car[]>(`${this.apiUrl}/search`, { params });
  }
  
}
