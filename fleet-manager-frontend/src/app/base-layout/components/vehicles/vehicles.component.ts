import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatDialog} from "@angular/material/dialog";
import {ActivatedRoute} from "@angular/router";
import {VehicleService} from "../../services/vehicle.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {ConfirmService} from "../../services/confirm.service";
import {DriversService} from "../../services/drivers.service";
import {PopupService} from "../../services/popup.service";
import {VehicledetailsComponent} from "../vehicledetails/vehicledetails.component";
import * as jwt_decode from 'jwt-decode';
@Component({
  selector: 'vehicles',
  templateUrl: './vehicles.component.html',
  styleUrls: ['./vehicles.component.scss']
})
export class VehiclesComponent implements OnInit, OnDestroy {
  vehicleGroup: FormGroup;
  private dataSource:any = [];
  private tableData :any= [];
  private backupData: any = [];
  public dialogRef;
  private fleetid;
  private types = ["CAR", "TRUCK", "MOTORCYCLE", "BICYCLE"];
  displayedColumns = ['vehicleName','vehicleType','vehicleFuel','vehicleSpeed','options'];
  private companyName: string = "";
  private companyID: string = "";
  private fleetName: string = "";
  driver = "";
  private sub:any;
  @ViewChild("newVehicle") newVehicle: any;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild("filter") filterInput:any;
  drivers = [];
  constructor(public dialog:MatDialog,
              public formBuilder:FormBuilder,
              public route:ActivatedRoute,
              public vehicleService:VehicleService,
              private dialogService: ConfirmService,
              private driverService: DriversService,
              private popupService:PopupService) {
    this.vehicleGroup = this.formBuilder.group({
      vehicleName:['',Validators.required],
      vehicleType:['',Validators.required],
      vehicleFuel:['',Validators.required],
      vehicleSpeed:['',Validators.required],
      driver: ['']
    });
  }

  ngOnInit() {
   this.sub = this.route.paramMap.subscribe(param=>{
      var fleetID = param.get('id');
      this.fleetid = fleetID;
      this.companyName = param.get('companyName');
      this.fleetName = param.get('fleetName');
      this.companyID = param.get('companyID');
         console.log(this.companyID);
      this.vehicleService.getAllVehiclesByFleetID(fleetID).subscribe(vehicles=>{
        console.log("Vehicles: ", vehicles);
        this.tableData = vehicles;
        this.updateTable(this.tableData);
        this.backupData = vehicles;
      });
    });
    this.driverService.getAllDriversByCompanyID(this.companyID).subscribe((drivers:any)=>{
      this.drivers = drivers;
    });
  }
  ngOnDestroy(): void{
    this.sub.unsubscribe();
  }
  openPopUp(){
    this.dialogRef = this.dialog.open(this.newVehicle,{
      width:'450px'
    });
  }
  closeModal(){
        if(this.dialogRef !=null){
            this.dialogRef.close();
            console.log('clicked');
        }
  }

  updateTable(data){
    this.dataSource = new MatTableDataSource<any>(data);
    this.dataSource.paginator = this.paginator;
  }
  addVehicle(){
        var decode = jwt_decode(localStorage.getItem("token"));
        var group = this.vehicleGroup.value;
        var username = decode.sub;
        var companyInfo = {
          companyID: this.companyID,
          companyName: this.companyName
        };

        var fleetInfo = {
          fleetID: this.fleetid,
          fleetName: this.fleetName
        }
        this.vehicleService.addVehicle(
            username,
            companyInfo,
            fleetInfo,
            group.driver,
            group.vehicleName,
            group.vehicleType,
            group.vehicleFuel,
            group.vehicleSpeed)
            .subscribe((vehicle:any) => {
                this.tableData.push(vehicle);
                this.updateTable(this.tableData);
                this.dataSource.paginator.pageIndex = this.dataSource.paginator.pageSize;
            });
        this.dialogRef.close();
        this.vehicleGroup.reset();

  }

  deleteVehicle(vehicle){
    this.dialogService.openDialog("Do you want to delete this Vehicle?").afterClosed().subscribe((res)=>{
      if (res){
        this.vehicleService.deleteVehicle(vehicle.vehicleID).subscribe((v:any)=>{
          const index = this.tableData.indexOf(vehicle);
          if (index > -1) {
            this.tableData.splice(index, 1);
            this.updateTable(this.tableData);
          }
        });
      }
    });
  }

  // OPEN POPUP WITH INFORMATION
  goToVehicle(vehicle){
        this.popupService.openPopUp(VehicledetailsComponent, vehicle, false);
  }

    // FILTER TABLE BY VEHICLE NAME
  doFilter(){
    var value = this.filterInput.nativeElement.value;
    if(value == ""){
      this.tableData = this.backupData;
    }
   else {
     this.tableData = this.tableData.filter((datum:any)=> {
     return datum.vehicleName.startsWith(value);
    });
   }
   this.updateTable(this.tableData);
  }
  toBeEdited:any
  editVehicles(vehicle) {
    this.toBeEdited = vehicle;
    this.openPopUp();
    this.vehicleGroup.patchValue({
      vehicleName: vehicle.vehicleName,
      vehicleType:vehicle.vehicleType,
      vehicleFuel:vehicle.vehicleFuel,
      vehicleSpeed:vehicle.vehicleSpeed,
      driver:vehicle.driver,
    });
    console.log(this.vehicleGroup);
  }

}
