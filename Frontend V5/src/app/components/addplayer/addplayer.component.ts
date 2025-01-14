import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Playerlistpojo } from 'src/app/playerlistpojo';
import { PlayerserviceService } from 'src/services/playerservice.service';
import { EventserviceService } from 'src/services/eventservice.service';

@Component({
  selector: 'app-addplayer',
  templateUrl: './addplayer.component.html',
  styleUrls: ['./addplayer.component.css']
})
export class AddplayerComponent implements OnInit {
  addplayerlist = new Playerlistpojo();
  sportslistarray:any;
  noDataFound='';


  constructor(private service:PlayerserviceService,private eventService:EventserviceService, private _route: Router) { }

  ngOnInit(): void {
    this.eventService.sportslist().subscribe(
      (resp) =>{
        console.log(resp);
        this.sportslistarray = resp;
        if(this.sportslistarray=='' || this.sportslistarray==null){
          this.noDataFound = 'No Data Found !!';
        }
      },
      error =>{
        console.log("error fetching Eventlist")
        this.noDataFound = 'No Data Found !!';
      }
    );
  }

  addplayer(){
          console.log(this.addplayerlist.sportsName);
    this.service.addPlayerToList(this.addplayerlist).subscribe
    (

      data=>{
        console.log("player added succcessfully");
        this._route.navigate(['/playerlist']);
      },
      error=>{
        console.log("player not added");
      }
    )
  }

}
