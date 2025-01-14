import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Playerlistpojo } from "src/app/playerlistpojo";
import { environment } from './../environments/environment';

@Injectable({
  providedIn: "root",
})
export class PlayerserviceService {
  sportslist: any;
  sports_url=environment.player;
  constructor(private http: HttpClient) {}
  playerlist() {
    return this.http.get(this.sports_url+"/players");
  }

  getPlayer(id: number) {
    return this.http.get<Playerlistpojo>(this.sports_url+"/players/" + id ,{responseType: 'json'});
  }
  

  public addPlayerToList(addplayerlist: Playerlistpojo) {
    return this.http.post<any>(
      this.sports_url+"/addPlayer",
      addplayerlist
    );
  }

  public deletePlayer(id: number) {
    return this.http.delete<any>(this.sports_url+"/deletePlayer/" + id);
  }
}
