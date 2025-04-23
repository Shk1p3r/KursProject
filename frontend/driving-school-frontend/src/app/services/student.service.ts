import { Injectable } from '@angular/core';
import { Student } from '../interfaces/student';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  private apiUrl = `${environment.apiUrl}/students`;

  constructor(private http: HttpClient) {}

  createStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(this.apiUrl, student);
  }

  getStudentById(id: number): Observable<Student> {
    return this.http.get<Student>(`${this.apiUrl}/${id}`);
  }

  getAllStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.apiUrl);
  }

  updateStudent(student: Student): Observable<Student> {
    return this.http.put<Student>(this.apiUrl, student);
  }

  deleteStudent(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  searchStudents(dateOfBirth?: string, fio?: string, phone?: string, groupNumber?: string): Observable<Student[]> {
    const params: any = {};
    if (dateOfBirth) params.dateOfBirth = dateOfBirth;
    if (fio) params.fio = fio;
    if (phone) params.phone = phone;
    if (groupNumber) params.groupNumber = groupNumber;
    return this.http.get<Student[]>(`${this.apiUrl}/search`, { params });
  }
}
