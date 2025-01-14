import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Participantslistpojo } from "src/app/participantslistpojo";
import { Observable } from 'rxjs';
import { environment } from "src/environments/environment";

@Injectable({
  providedIn: "root",
})
export class ParticipationserviceService {
  participation_url=environment.participation;
  constructor(private http: HttpClient) {}

  participationlist() {
    return this.http.get(this.participation_url+"/getParticipations");
  }

  public addParticipantToList(addplayerlist: Participantslistpojo):Observable<Object> {
    addplayerlist.status = "pending";
    console.log("addplayerlist="+addplayerlist.status);
    return this.http.post(
      this.participation_url+"/addParticipation",
      addplayerlist
    );
  }

  public approval(id: number, approved: any) {
    console.log("Approval ID="+id+"Status="+approved.status);
    return this.http.put(this.participation_url+"/updateStatus/" + id + "/" + approved.status,approved);
  }

  public participationStatus(status: any) {
    return this.http.get(
      this.participation_url+"/getApprovedParticipations/" + status
    );
  }
}
