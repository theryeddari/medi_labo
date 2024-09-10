import {Component, OnInit, Renderer2} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {NoteService} from "../../service/note/note.service";
import {tap} from "rxjs";
import {NoteResponse} from "../../model/patient/note-response.model";
import {NoteRequest} from "../../model/patient/note-request.model";
import {ReportService} from "../../service/report/report.service";

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})
export class NoteComponent implements OnInit {
  patientId?: string | null;  // Propriété peut être undefined
  currentDateTextarea: string = new Date().toLocaleDateString();
  currentDate: string = new Date().toISOString();
  newNote: { text: string } = {text: ''};
  notes: NoteResponse[] = [];
  emptyRows: number[] = [];
  processRequest?: string | null;
  reportPatient: string = "waiting report";

  constructor(
    private actualRoute: ActivatedRoute,
    private noteService: NoteService,
    private reportService: ReportService,
    private renderer: Renderer2,
    private route: Router,
  ) {
  }

  ngOnInit(): void {
    // Récupérer l'ID du client depuis les paramètres de la route
    this.patientId = this.actualRoute.snapshot.paramMap.get('patientId');

    if (this.patientId) {
      this.noteService.getNotes(Number(this.patientId)).pipe(tap(data => {
        this.notes = data.noteResponseList.map(note => {
          // Convertir la date en heure locale (ou dans le format que tu veux)
          console.log(note.date);
          const formattedDate = new Date(note.date).toLocaleString();  // Formatage simple
          return {
            ...note,
            date: formattedDate  // Mise à jour du champ "date" avec le formatage
          };
        });

        this.notes.sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime());

        this.updateEmptyRows();
      })).subscribe({
        error: (error) => {
          // Logique en cas d'erreur
          console.error('Error updating patient', error);
          this.processRequest = "Error updating note";
          this.updateEmptyRows();
        }
      });
      this.report()
    }
  }


  onSave() {
    if (!this.patientId) return;  // Assurer que patient et patientId sont définis

    const newNote: NoteRequest = {
      patientId: this.patientId,  // Assurez-vous que l'ID est un nombre
      date: this.currentDate,
      note: this.newNote.text,
    };


    this.noteService.postNote(newNote).pipe(
      tap(response => {
        // Logique après la mise à jour réussie
        this.processRequest = "Note saved Successfully";
        console.log('Note Saved successfully', response);

        this.notes = [{...response, date: new Date(response.date).toLocaleString()}, ...this.notes]
        this.report()
        setTimeout(() => {
          this.processRequest = null;
        }, 2000);
      })
    ).subscribe({
      error: (error) => {
        // Logique en cas d'erreur
        console.error('Error saving note', error);
        this.processRequest = "Error saving note";
      }
    });
  }

  updateEmptyRows(): void {
    const minimumRows = 5;
    const currentRows = this.notes.length;
    const rowsToAdd = minimumRows - currentRows;
    if (rowsToAdd > 0) {
      this.emptyRows = Array(rowsToAdd).fill(null);
    }
  }

  adjustHeight(event: Event): void {
    const scrollHeight = (event.target as HTMLTextAreaElement).scrollHeight + 'px'; // Calculer la nouvelle hauteur
    const container = document.querySelector('.textarea-container textarea') as HTMLTextAreaElement;
    this.renderer.setStyle(container, 'height', scrollHeight);

  }

  report() {
    this.reportService.getReport(Number(this.patientId)).pipe(tap(data => {
      this.reportPatient = data.riskEstimation;
      this.updateBackgroundColor();
    })).subscribe({
      error: (error) => {
        // Logique en cas d'erreur
        console.error('Error getting report', error);
        this.processRequest = "Error getting report";
      }
    });
  }

  updateBackgroundColor(): void {
    let color;  // Couleur par défaut
    if (this.reportPatient === 'Early onset') {
      color = 'red';
    } else if (this.reportPatient === 'Danger') {
      color = 'orange';
    } else if (this.reportPatient === 'Borderline') {
      color = 'yellow';
    } else if (this.reportPatient === 'None') {
      color = 'green';
    }

    // Applique la couleur à la variable CSS
    const container = document.querySelector('.risk-subtitle') as HTMLElement;
    if (container) {
      this.renderer.setStyle(container, 'background-color', color);
    }
  }

  onBack() {
    this.route.navigate(['/patient/', this.patientId]);
  }

}
