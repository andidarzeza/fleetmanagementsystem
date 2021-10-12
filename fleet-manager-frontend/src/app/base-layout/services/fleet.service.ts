import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
const APIUrl = environment.APIUrl;
@Injectable({
  providedIn: 'root'
})
export class FleetService {

  constructor(private http:HttpClient) { }

  getAllFleetsByCompanyID(companyID){
    return this.http.get(APIUrl +"/fleets/" + companyID);
  }
  addFleet(companyID, fleetName){
    return this.http.post(APIUrl +"/fleets/add",{companyID:companyID,fleetName:fleetName});
  }

  deleteFleet(fleetID){
    return this.http.delete(APIUrl +"/fleets/delete/" + fleetID);
  }

}
