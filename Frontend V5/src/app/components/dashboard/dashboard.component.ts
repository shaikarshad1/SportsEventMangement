import { Component, OnInit } from '@angular/core';
import { LoginserviceService } from 'src/services/loginservice.service';
import { PlayerserviceService } from 'src/services/playerservice.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  userNameDisplay:string | null | undefined;

  constructor(private playerService:PlayerserviceService,private loginservice:LoginserviceService) { }

  ngOnInit(): void {
    let token = this.loginservice.getToken();
    console.log("this is my token "+token);
    this.userNameDisplay = localStorage.getItem("userName");
  }

  // playerlist(){
  //   this.playerService.playerlist().subscribe(
  //     resp=>{
  //       console.log(resp)
  //     },
  //     error=>{
  //       console.log(error)
  //     }
  //   )
  // }

}
