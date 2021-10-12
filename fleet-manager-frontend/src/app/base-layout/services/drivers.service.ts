import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
const APIUrl = environment.APIUrl;
@Injectable({
  providedIn: 'root'
})
export class DriversService {

  constructor(private http:HttpClient) { }

  getAllDrivers() {
    return this.http.get(APIUrl+ "/drivers/all");
  }

  addDriver(driver) {
    return this.http.post(APIUrl+ "/drivers/add",driver);
  }

  getDriver(driverID){
    return this.http.get(APIUrl + "/drivers/" + driverID);
  }

  deleteDriver(driverID){
    return this.http.delete(APIUrl+ "/drivers/delete/"+driverID);
  }
  getAllDriversByCompanyID(companyID){
    return this.http.get(APIUrl + "/drivers/getByCompanyID/" + companyID);
  }
}
