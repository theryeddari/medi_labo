import {Component, OnInit} from '@angular/core';
import {PatientService} from "../../service/patient/patient.service";
import {Router} from '@angular/router';
import {tap} from "rxjs";

@Component({
  selector: 'app-clientele',
  templateUrl: './clientele.component.html',
  styleUrls: ['./clientele.component.css']
})
export class ClienteleComponent implements OnInit {
  clients: any[] = [];
  emptyRows: number[] = [];
  processRequest?: string;


  constructor(private patientService: PatientService, private router: Router) {
  }

  ngOnInit(): void {
    this.patientService.getClientele().pipe(tap(data => {
      this.clients = data.clienteleIdentity;
      this.updateEmptyRows();
    })).subscribe({
      error: (error) => {
        // Logique en cas d'erreur
        console.error('Error updating patient', error);
        this.processRequest = "Error updating patient";
        this.updateEmptyRows();
      }
    });
  }

  updateEmptyRows(): void {
    const minimumRows = 7;
    const currentRows = this.clients.length;
    const rowsToAdd = minimumRows - currentRows;
    this.emptyRows = Array(rowsToAdd).fill(null);
  }

  onClientClick(patientId: number): void {
    // Naviguer vers la page de détails avec l'ID du client en tant que paramètre
    this.router.navigate(['/patient', patientId]);
  }
}
