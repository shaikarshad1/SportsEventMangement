import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PlayerserviceService } from 'src/services/playerservice.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-playerlist',
  templateUrl: './playerlist.component.html',
  styleUrls: ['./playerlist.component.css']
})
export class PlayerlistComponent implements OnInit {

  playerarray:any;
  noDataDisplay='';
  constructor(private service:PlayerserviceService,private _route: Router) { }

  ngOnInit(): void {
    this.service.playerlist().subscribe(
      (resp) =>{
        console.log(resp);
        this.playerarray = resp;
        if(this.playerarray=='' || this.playerarray==null){
          this.noDataDisplay = "No Data Found, Please add Player";
        }
      },
      error =>{
        console.log("error fetching playerlist")
        this.noDataDisplay = "No Data Found, Please add Player";
      }
      
    );
  }

  deletePlayer(player: any,playerId: any){
    this.service.deletePlayer(player.playerId).subscribe(
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
  alertConfirmation(player: { playerId: number; },playerId: any) {
    Swal.fire({
      title: 'Are you sure?',
      text: 'This process is irreversible.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, go ahead.',
      cancelButtonText: 'No, let me think',
    }).then((result) => {
      if (result.value) {
        Swal.fire('Removed!', 'Player removed successfully.', 'success');
        this.deletePlayer(player,playerId);
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('Cancelled', 'Player is still in our database.)', 'error');
      }
    });
  }

}
