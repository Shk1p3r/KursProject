import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Exam } from '../interfaces/exam';

@Injectable({
  providedIn: 'root',
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
  searchExams(
    date?: string,
    type?: string,
    result?: string
  ): Observable<Exam[]> {
    let params: any = {};
    if (date) params.date = date;
    if (type) params.type = type;
    if (result) params.result = result;
    return this.http.get<Exam[]>(`${this.apiUrl}/search`, { params });
  }
}
