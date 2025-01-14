import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Participantslistpojo } from 'src/app/participantslistpojo';
import { Playerlistpojo } from 'src/app/playerlistpojo';
import { ParticipationserviceService } from 'src/services/participationservice.service';
import { PlayerserviceService } from 'src/services/playerservice.service';
import { EventserviceService } from 'src/services/eventservice.service';

@Component({
  selector: 'app-add-participation',
  templateUrl: './add-participation.component.html',
  styleUrls: ['./add-participation.component.css']
})
export class AddParticipationComponent implements OnInit {

  addParticipanToList = new Participantslistpojo();
  playerData = new Playerlistpojo();
  eventList:any;
  notfound='';
  id_string:any;
  public validId2=true;
  public validId=false;
  constructor(private service:ParticipationserviceService,private playerService:PlayerserviceService,private eventService:EventserviceService,  private _route: Router) { }

  ngOnInit(): void {

    this.eventService.event().subscribe(
      (resp) =>{
        console.log(resp);
        this.eventList = resp;
        console.log(this.eventList);
        if(this.eventList=='' || this.eventList==null){
          //this.eventList = 'No Data Found !!';
        }
      },
      error =>{
        console.log("error fetching Eventlist")
        //this.eventList = 'No Data Found !!';
      }
    );
  }

  getInfo(id:number)
  {
    console.log("Working");
    console.log(id);
    this.id_string=id.toString();
    if(this.id_string.length==''){
      console.log("It's Blank bruh");
      this.validId=false;
      this.playerData= new Playerlistpojo();
    }
    else{
    this.playerService.getPlayer(id).subscribe(
      (data) =>{
        console.log(data);
        this.playerData = data;
        this.validId=true;
        this.validId2=true;
        if( this.playerData==null){
          this.notfound = "No Data Found, Please add player";
          this.validId=false;
          this.validId2=false;
          this.playerData= new Playerlistpojo();
        }
        console.log(this.playerData);
        if(id==null || id==undefined){
          console.log("Blank ID");
          this.validId=false;
          this.playerData= new Playerlistpojo();
        }
      },
      error =>{
        console.log("error fetching playerlist")
        this.notfound = "No Data Found, Please add player";
        this.validId=false;
        this.validId2=false;
        this.playerData= new Playerlistpojo();
      }
    );
    }
  }

  addparticipant(){
    this.service.addParticipantToList(this.addParticipanToList).subscribe
    (
      data=>{
        console.log("Participant added succcessfully");
        this._route.navigate(['/participationList']);
      },
      error=>{
        console.log("Participant not added");
      }
    );
  }

}
