import { Component, OnInit } from '@angular/core';
import { LoginserviceService } from 'src/services/loginservice.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public loggedIn=false;
  constructor(private loginService:LoginserviceService) { }

  ngOnInit(): void {
    this.loggedIn = this.loginService.isLoggedIn();
  }

}
