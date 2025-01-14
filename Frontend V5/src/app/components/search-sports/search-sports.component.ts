import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EventserviceService } from 'src/services/eventservice.service';

@Component({
  selector: 'app-search-sports',
  templateUrl: './search-sports.component.html',
  styleUrls: ['./search-sports.component.css']
})
export class SearchSportsComponent implements OnInit {

  searchSport!: string;
  sportsFetchedData:any;
  noDataDisplay='';
  data=false;
  constructor(private service:EventserviceService,  private route: Router) { }

  ngOnInit(): void {
  }

  searchBySportsName(){
    this.service.searchSportsByName(this.searchSport).subscribe
    (

      (resp) =>{
        console.log(resp);
        this.sportsFetchedData = resp;
        this.data=true;
        if(this.sportsFetchedData=='' || this.sportsFetchedData==null){
          this.noDataDisplay = 'No Data Found !!';
          this.data=false;
        }
      },
      error =>{
        console.log("error fetching Eventlist")
        this.noDataDisplay = 'No Data Found !!';
        this.data=false;
      }
    )
  }

}
