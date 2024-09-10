import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {NotesResponse} from "../../model/patient/notes-response.model";
import {NoteRequest} from "../../model/patient/note-request.model";

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getNotes(patientId: number): Observable<NotesResponse> {
    const headers = this.getAuthHeaders();
    return this.http.get<NotesResponse>(`${this.apiUrl}/api/clientele/patient/notes/${patientId}`, {headers});
  }

  postNote(note: NoteRequest): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.post(`${this.apiUrl}/api/clientele/patient/notes/note`, note, {headers});
  }

  private getAuthHeaders(): HttpHeaders {
    const authCredentials = localStorage.getItem('authCredentials');
    return new HttpHeaders().set('Authorization', 'Basic ' + authCredentials);
  }

}
