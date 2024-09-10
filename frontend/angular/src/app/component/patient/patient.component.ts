import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {PatientResponse} from "../../model/patient/patient-response.model";
import {PatientService} from "../../service/patient/patient.service";
import {PatientRequest} from "../../model/patient/patient-request.model";
import {tap} from "rxjs";

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {
  patient?: PatientResponse;  // Propriété peut être undefined
  patientId?: string | null;  // Propriété peut être undefined
  processRequest?: string | null;

  constructor(
    private actualRoute: ActivatedRoute,
    private patientService: PatientService,
    private route: Router,
  ) {
  }

  ngOnInit(): void {
    // Récupérer l'ID du client depuis les paramètres de la route
    this.patientId = this.actualRoute.snapshot.paramMap.get('patientId');

    if (this.patientId) {
      this.patientService.getPatient(Number(this.patientId)).subscribe(data => {
        this.patient = {
          ...data, birthdate: new Date(data.birthdate).toLocaleString('en-US', {hour12: false}),
        }
      });
    }

  }

  onSave() {
    if (!this.patient || !this.patientId) return;  // Assurer que patient et patientId sont définis
    const updatedPatient: PatientRequest = {
      patientId: this.patientId,
      name: this.patient.name,
      username: this.patient.username,
      birthdate: new Date(Date.parse(this.patient.birthdate)).toISOString(),
      gender: this.patient.gender,
      address: this.patient.address,
      phone: this.patient.phone
    };
    this.patientService.postPatient(updatedPatient).pipe(
      tap(response => {
        // Logique après la mise à jour réussie
        this.processRequest = "Patient Updated Successfully";
        console.log('Patient updated successfully', response);
        setTimeout(() => {
          this.processRequest = null;
        }, 2000);
      })
    ).subscribe({
      error: (error) => {
        if (error.status === 400) {
          console.error('Bad Request during updating patient (missing parameter', error);
          this.processRequest = "Error updating patient, please check necessary parameter";
        } else {
          // Logique en cas d'erreur
          console.error('Error updating patient', error);
          this.processRequest = "Error updating patient";
        }
      }
    });
  }

  onBack() {
    this.route.navigate(['/clientele']);
  }

  onViewNotes() {
    this.route.navigate(['/note', this.patientId]); // Assurez-vous que la route '/notes' existe
  }
}
