import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StatementRequestApiService {

  constructor(private http: HttpClient) { }

  public getById(requestId: String): Observable<any> {
    return this.http.get(`requests/${requestId}`);
  }

  public getAll(): Observable<any> {
    return this.http.get(`requests`);
  }
  
}
