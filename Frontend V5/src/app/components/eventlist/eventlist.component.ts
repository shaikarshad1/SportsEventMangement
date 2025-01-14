import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EventserviceService } from 'src/services/eventservice.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-eventlist',
  templateUrl: './eventlist.component.html',
  styleUrls: ['./eventlist.component.css']
})
export class EventlistComponent implements OnInit {

  Eventarray:any;
  constructor(private service:EventserviceService,private _route: Router,public datepipe: DatePipe) { }

  ngOnInit(): void {
    this.service.event().subscribe(
      (resp) =>{
        console.log(resp);
        this.Eventarray = resp;
      },
      error =>console.log("error fetching Eventlist")
    );
  }

  // formatDate(date:string){
  //   date=new Date();
  //   let latest_date =this.datepipe.transform(this.date, 'yyyy-MM-dd');
  //  }

  deleteEvent(Event: { eventId: number; },eventId:number){
    this.service.deleteEvent(Event.eventId).subscribe(
      (resp)=>{
        console.log(resp);
        this.ngOnInit();
      },
      error=>{
        console.log(error);
        this.ngOnInit();
      }     
    );
  }
  alertConfirmation(Event: { eventId: number; },eventId:number) {
    Swal.fire({
      title: 'Are you sure?',
      text: 'This process is irreversible.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, go ahead.',
      cancelButtonText: 'No, let me think',
    }).then((result) => {
      if (result.value) {
        Swal.fire('Removed!', 'Event removed successfully.', 'success');
        this.deleteEvent(Event,eventId);
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('Cancelled', 'Event is still in our database.)', 'error');
      }
    });
  }

}
