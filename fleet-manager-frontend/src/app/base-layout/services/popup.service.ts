import {Component, Injectable} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {ComponentType} from "ngx-toastr";
import {VehicledetailsComponent} from "../components/vehicledetails/vehicledetails.component";

@Injectable({
  providedIn: 'root'
})
export class PopupService {

  constructor(private dialog: MatDialog) { }

  openPopUp(component, data, disableClose){
    this.dialog.open(component, {
      panelClass: 'create-chart',
      disableClose: disableClose,
      data: data
    });
  }
}
