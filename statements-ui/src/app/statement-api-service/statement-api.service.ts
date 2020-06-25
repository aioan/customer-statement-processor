import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StatementApiService {

  constructor(private http: HttpClient) { }

  public uploadFile(file: any): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);
    return this.http.post('statements', formData);
  }
}
