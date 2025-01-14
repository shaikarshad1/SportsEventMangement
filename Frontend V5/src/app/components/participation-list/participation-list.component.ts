import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ParticipationserviceService } from 'src/services/participationservice.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-participation-list',
  templateUrl: './participation-list.component.html',
  styleUrls: ['./participation-list.component.css']
})
export class ParticipationListComponent implements OnInit {

  participationarray:any;
  noDataDisplay='';
  status='';
  constructor(private service:ParticipationserviceService,private _route: Router) { }

  ngOnInit(): void {
    this.service.participationlist().subscribe(
      (resp) =>{
        console.log(resp);
        this.participationarray = resp;
        this.status = this.participationarray.status;
        console.log(this.participationarray);
        if(this.participationarray=='' || this.participationarray==null){
          this.noDataDisplay = "No Data Found, Please add player";
        }
      },
      error =>{
        console.log("error fetching playerlist")
        this.noDataDisplay = "No Data Found, Please add player";
      }
      
    );
  }
  approval(player: { participation_id: number; },participation_id: any){
    const approve={
      "participation_id":participation_id,
      "status":"Approved"
    }
    this.service.approval(player.participation_id,approve).subscribe(
      (resp) =>{
        console.log(resp);
        this.ngOnInit();
        this.successNotification("Approved","Participation Is Approved");
      },
      error =>{
        console.log("Not Approved")
      }
      
    );

  }

  decline(player: { participation_id: number; },participation_id: any){
    const decline={
      "participation_id":participation_id,
      "status":"Declined"
    }
    this.service.approval(player.participation_id,decline).subscribe(
      (resp) =>{
        console.log(resp);
        this.ngOnInit();
        this.successNotification("Declined","Participation Is Declined");
      },
      error =>{
        console.log("Not Approved")
      }
      
    );

  }

  // deleteparticipation(player: { playerId: number; },playerId: any){
  //   this.service.deleteparticipation(player.playerId).subscribe(
  //     (resp)=>{
  //       console.log(resp);
  //       this.ngOnInit();
  //     },
  //     error=>{
  //       console.log(error);
  //       this.ngOnInit();
  //     }     
  //   );
  // }
  tinyAlert() {
    Swal.fire('Hey there!');
  }
  successNotification(status:string,message:string) {
    Swal.fire(status, message, 'success');
  }


}
