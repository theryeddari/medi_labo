import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ReportResponse} from "../../model/patient/report-response.model";

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getReport(patientId: number): Observable<ReportResponse> {
    const headers = this.getAuthHeaders();
    return this.http.get<ReportResponse>(`${this.apiUrl}/api/clientele/patient/notes/report/${patientId}`, {headers});
  }

  private getAuthHeaders(): HttpHeaders {
    const authCredentials = localStorage.getItem('authCredentials');
    return new HttpHeaders().set('Authorization', 'Basic ' + authCredentials);
  }
}
