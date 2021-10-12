import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
const APIUrl = environment.APIUrl;
@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  constructor(private http:HttpClient) { }

  getAllVehiclesByFleetID(fleetID){
    return this.http.get(APIUrl+ "/vehicles/" +fleetID);
  }

  addVehicle(userID, companyInfo, fleetInfo, driver, vehicleName,vehicleType,vehicleFuel,vehicleSpeed) {
    return this.http.post(APIUrl+ "/vehicles/add",{
      userID: userID,
      companyInfo: companyInfo, fleetInfo: fleetInfo, vehicleName:vehicleName,type:vehicleType,fuel:vehicleFuel,speed:vehicleSpeed,
      driverID: driver, position: {x:0, y: 0}
    });
  }

  deleteVehicle(vehicleID){
    return this.http.delete(APIUrl+ "/vehicles/delete/"+ vehicleID);
  }

  updateVehicle(vehicleID, vehicleName, vehicleType, vehicleFuel, vehicleSpeed) {
    return this.http.put(APIUrl+ "/vehicles/update/"+vehicleID,{
      vehicleName: vehicleName,type: vehicleType,fuel: vehicleFuel,speed: vehicleSpeed
    });
  }

  addPosition(vehicleID){
    return this.http.post(APIUrl + "/positions/add", {vehicleID: vehicleID, x: 0, y: 0});
  }

}
