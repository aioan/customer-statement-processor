import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class StatementReportApiService {
  BASE_PATH = `api/reports`;

  constructor(private http: HttpClient) { }

  public getById(requestId: String): Observable<any> {
    return this.http.get(`${this.BASE_PATH}/${requestId}`);
  }

  public getAll(): Observable<any> {
    return this.http.get(`${this.BASE_PATH}`);
  }
  
}
