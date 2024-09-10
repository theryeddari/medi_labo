import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ClientResponse} from '../../model/patient/clientele-response.model';
import {PatientResponse} from "../../model/patient/patient-response.model";
import {PatientRequest} from "../../model/patient/patient-request.model";

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getClientele(): Observable<ClientResponse> {
    const headers = this.getAuthHeaders();
    return this.http.get<ClientResponse>(`${this.apiUrl}/api/clientele`, {headers});
  }

  getPatient(patientId: number): Observable<PatientResponse> {
    const headers = this.getAuthHeaders();
    return this.http.get<PatientResponse>(`${this.apiUrl}/api/clientele/patient/${patientId}`, {headers});
  }

  postPatient(patient: PatientRequest): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.post(`${this.apiUrl}/api/clientele/patient`, patient, {headers});
  }

  private getAuthHeaders(): HttpHeaders {
    const authCredentials = localStorage.getItem('authCredentials');
    return new HttpHeaders().set('Authorization', 'Basic ' + authCredentials);
  }
}
