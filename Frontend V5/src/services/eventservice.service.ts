import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Eventlistpojo } from "src/app/eventlistpojo";
import { environment } from './../environments/environment';

@Injectable({
  providedIn: "root",
})
export class EventserviceService {
  event_url= environment.sports;
  constructor(private http: HttpClient) {}

  event() {
    return this.http.get(this.event_url+"/events");
  }

  public addEventToList(addEventList: Eventlistpojo) {
    console.log("addEventList="+addEventList);
    return this.http.post<any>(this.event_url+"/addEvent", addEventList);
  }

  public deleteEvent(id: number) {
    return this.http.delete<any>(this.event_url+"/deleteEvent/" + id);
  }

  public searchByName(searchByName: string) {
    return this.http.get(this.event_url+"/events/" + searchByName);
  }

  public sportslist() {
    return this.http.get(this.event_url+"/sports");
  }

  public searchSportsByName(sportsName: string) {
    return this.http.get(this.event_url+"/sports/" + sportsName);
  }
}
