import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Exam } from '../interfaces/exam';

@Injectable({
  providedIn: 'root'
})
export class ExamService {
  private apiUrl = `${environment.apiUrl}/exams`;

  constructor(private http: HttpClient) {}
  createExam(exam: Exam): Observable<Exam> {
    return this.http.post<Exam>(this.apiUrl, exam);
  }

  getExamById(id: number): Observable<Exam> {
    return this.http.get<Exam>(`${this.apiUrl}/${id}`);
  }

  getAllExams(): Observable<Exam[]> {
    return this.http.get<Exam[]>(this.apiUrl);
  }

  updateExam(exam: Exam): Observable<Exam> {
    return this.http.put<Exam>(this.apiUrl, exam);
  }

  deleteExam(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getExamsByDate(date: string): Observable<Exam[]> {
    return this.http.get<Exam[]>(`${this.apiUrl}/date/${date}`);
  }

  getExamsByType(type: string): Observable<Exam[]> {
    return this.http.get<Exam[]>(`${this.apiUrl}/type/${type}`);
  }

  getExamsByResult(result: string): Observable<Exam[]> {
    return this.http.get<Exam[]>(`${this.apiUrl}/result/${result}`);
  }
}
