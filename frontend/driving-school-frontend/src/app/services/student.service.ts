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

  getStudentsByFio(fio: string): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.apiUrl}/fio/${fio}`);
  }

  getStudentsByPhone(phone: string): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.apiUrl}/phone/${phone}`);
  }

  getStudentsByGroupNumber(groupNumber: string): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.apiUrl}/group/${groupNumber}`);
  }

  getStudentsByDateOfBirth(dateOfBirth: string): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.apiUrl}/dateOfBirth/${dateOfBirth}`);
  }
}
