import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EventserviceService } from 'src/services/eventservice.service';

@Component({
  selector: 'app-sportslist',
  templateUrl: './sportslist.component.html',
  styleUrls: ['./sportslist.component.css']
})
export class SportslistComponent implements OnInit {

  // event and sports list are one microservice so I am not creating different services in angular 
  // using the same Eventservices 
  sportslistarray:any;
  noDataFound='';
  constructor(private service:EventserviceService,private _route: Router) { }

  ngOnInit(): void {
    this.service.sportslist().subscribe(
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

}
