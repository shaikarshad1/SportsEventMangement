import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ParticipationserviceService } from 'src/services/participationservice.service';

@Component({
  selector: 'app-participation-status',
  templateUrl: './participation-status.component.html',
  styleUrls: ['./participation-status.component.css']
})
export class ParticipationStatusComponent implements OnInit {

  participationarray:any;
  participationdeclined:any;
  participationPending:any;

  noDataDisplay='';
  noDataDisplay1='';
  noDataDisplay2='';
  selectedType : any;
  statusApproved="Approved";
  statusDeclined="Declined";
  statusPending="pending";
  approvedData=false;
  declinedData=false;
  pendingData=false;
  
  displayApproved=false;
  displayDeclined=false;
  displayPending=false;

  constructor(private service:ParticipationserviceService,private _route: Router) { }

  ngOnInit(): void {
    this.service.participationStatus(this.statusApproved).subscribe(
      (resp) =>{
        console.log(resp);
        this.participationarray = resp;
        this.approvedData=true;
        if(this.participationarray=='' || this.participationarray==null){
          this.approvedData=false;
          this.noDataDisplay = "No Data Found, Please Approve the Participants";
        }
      },
      error =>{
        console.log("error fetching playerlist")
        this.approvedData=false;
        this.noDataDisplay = "No Data Found, Please Approve the Participants";
      }
      
    );

    this.service.participationStatus(this.statusDeclined).subscribe(
      (resp) =>{
        console.log(resp);
        this.participationdeclined = resp;
        this.declinedData=true;
        if(this.participationdeclined=='' || this.participationdeclined==null){
          this.declinedData=false;
          this.noDataDisplay1 = "No declined participants";
        }
      },
      error =>{
        console.log("error fetching playerlist")
        this.declinedData=false;
        this.noDataDisplay1 = "No declined participants";
      }
      
    );

    this.service.participationStatus(this.statusPending).subscribe(
      (resp) =>{
        console.log(resp);
        this.participationPending = resp;
        this.pendingData=true;
        if(this.participationPending=='' || this.participationPending==null){
          this.pendingData=false;
          this.noDataDisplay2 = "No Pending participants";
        }
      },
      error =>{
        console.log("error fetching playerlist")
        this.pendingData=false;
        this.noDataDisplay2 = "No Pending participants";
      }
      
    );
    
  }


  // display(status:any) {
  //   // this.selectedType = status;
  //   // console.log(this.selectedType);
  // }
  display(status:any)
  {
    console.log(status.target.value);
    if (status.target.value=='Approved')
    {
      this.displayApproved=true;
      this.displayDeclined=false;
      this.displayPending=false;
      console.log("Approved"+this.displayApproved);
    }
    else if (status.target.value=='Declined')
    {
      this.displayApproved=false;
      this.displayDeclined=true;
      this.displayPending=false;
    }
    else if (status.target.value=='Pending')
    {
      this.displayApproved=false;
      this.displayDeclined=false;
      this.displayPending=true;
    }
  }

  

}
