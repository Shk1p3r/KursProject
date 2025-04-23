import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Instructor } from '../interfaces/instructor';
import { Category } from '../interfaces/category';

@Injectable({
  providedIn: 'root',
})
export class InstructorService {
  private apiUrl = `${environment.apiUrl}/instructors`;

  constructor(private http: HttpClient) {}
  createInstructor(instructor: Instructor): Observable<Instructor> {
    return this.http.post<Instructor>(this.apiUrl, instructor);
  }

  getInstructorById(id: number): Observable<Instructor> {
    return this.http.get<Instructor>(`${this.apiUrl}/${id}`);
  }

  getAllInstructors(): Observable<Instructor[]> {
    return this.http.get<Instructor[]>(this.apiUrl);
  }

  updateInstructor(instructor: Instructor): Observable<Instructor> {
    return this.http.put<Instructor>(this.apiUrl, instructor);
  }

  deleteInstructor(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  searchInstructors(fio: string, seniority: number | null): Observable<Instructor[]> {
    const params: any = {};
    if (fio) params['fio'] = fio;
    if (seniority != null) params['seniority'] = seniority;
    return this.http.get<Instructor[]>(`${this.apiUrl}/search`, { params });
  }
}
