import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StatementErrorApiService {

  constructor(private http: HttpClient) { }

  public getErrors(requestId: String): Observable<any> {
    return this.http.get(`statementErrors/${requestId}`);
  }
}
