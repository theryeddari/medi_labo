import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from "../../service/auth/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {
  }

  onSubmit() {
    this.authService.login(this.username, this.password).subscribe({
      next: (response) => {
        if (response.status === 200) {
          // Connexion réussie, stocker les identifiants dans le localStorage
          localStorage.setItem('authCredentials', btoa(this.username + ':' + this.password));
          this.router.navigate(['/clientele']);
        }
      },
      error: (error) => {
        if (error.status === 401) {
          this.errorMessage = 'Invalid credentials';
        } else {
          this.errorMessage = 'An error occurred';
        }
      }
    });
  }
}
