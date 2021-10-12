import {Component, Inject, OnInit, Optional} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {DriversService} from "../../services/drivers.service";

@Component({
  selector: 'vehicledetails',
  templateUrl: './vehicledetails.component.html',
  styleUrls: ['./vehicledetails.component.scss']
})
export class VehicledetailsComponent implements OnInit {
  constructor(@Inject(MAT_DIALOG_DATA) @Optional() public data: any,
              private driverService: DriversService) { }

  vehicle = {
    vehicleName: "",
    type: "",
    fuel: 0
  };
  driver = {
    firstName: "",
    lastName: "",
    driverAge: 0,
    driverAddress: "",
    profilePhoto: ""
  };

  ngOnInit() {
    this.vehicle = this.data;
    console.log(this.data);
    if(this.data.driverID!==""){
      this.driverService.getDriver(this.data.driverID).subscribe((response:any)=>{
        this.driver = response;
      });
    }
    
  }

}
