import {AfterViewInit, Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {FleetService} from "../../services/fleet.service";
import {ActivatedRoute,Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatDialog} from "@angular/material/dialog";
import {MatPaginator} from "@angular/material/paginator";
import {CompanyService} from "../../services/company.service";
import {MatTableDataSource} from "@angular/material/table";
import {ConfirmService} from "../../services/confirm.service";

@Component({
  selector: 'company-details',
  templateUrl: './company-details.component.html',
  styleUrls: ['./company-details.component.scss']
})
export class CompanyDetailsComponent implements OnInit, OnDestroy{

  private tableData :any= [];
  private dataSource:any = [];
  fleetGroup:FormGroup;
  public dialogRef;
  private sub: any;
  companyid: any;
  // VIEW_CHILD FOR ADDING NEW FLEET
  @ViewChild("newFleet") newFleet: any;
  // VIEW_CHILD  MAT PAGINATOR
  @ViewChild(MatPaginator) paginator: MatPaginator;
  //VIEW_CHILD FILTER
  @ViewChild("filter") filterInput:any;
  private backupData: any = [];
  private companyName: string = "";
  // TABLE COLUMNS
  displayedColumns = ['companyID', 'fleetName','options'];

  constructor(private fleetService:FleetService, private route:ActivatedRoute,
              public formBuilder:FormBuilder,public dialog:MatDialog, public companyService:CompanyService,
              public router:Router, private dialogService: ConfirmService) {
    this.fleetGroup = this.formBuilder.group({
      fleetName:['',Validators.required],
    });
  }

  ngOnInit() {
   this.sub = this.route.paramMap.subscribe(param =>{
      var companyID = param.get('id');
      this.companyName = param.get('companyName');
      this.companyid = companyID;
      this.fleetService.getAllFleetsByCompanyID(companyID).subscribe((fleets: any) =>{
        console.log(fleets);
        this.tableData = fleets;
        this.backupData = fleets;
        this.updateTable(this.tableData);
      });
    });
  }
  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  addFleet() {
    this.fleetService.addFleet(this.companyid,this.fleetGroup.value.fleetName).subscribe(fleet=>{
      this.tableData.push(fleet);
      this.updateTable(this.tableData);
      this.dataSource.paginator.pageIndex = this.dataSource.paginator.pageSize;
    }, err => {
      console.log(err);
    });
    this.dialogRef.close();
    this.fleetGroup.reset();
  }

  deleteFleet(fleet) {
    this.dialogService.openDialog("Do you want to delete this fleet?").afterClosed().subscribe((res)=>{
      if (res){
        this.fleetService.deleteFleet(fleet.fleetID).subscribe((response:any)=>{
          const index = this.tableData.indexOf(fleet);
          if (index > -1) {
            this.tableData.splice(index, 1);
            this.updateTable(this.tableData);
          }
        });
      }
    });
  }

  // OPEN AND CLOSE METHODS FOR FLEET POPUP
  openPopUp() {
    this.dialogRef = this.dialog.open(this.newFleet,{
      width:'450px'});
  }
  closeModal(){
    if(this.dialogRef !=null){
      this.dialogRef.close();
      console.log('closed');
    }
  }
  // NAVIGATE TO DRIVERS
  goToDrivers(){
    this.router.navigate(['/drivers', this.companyid]);
  }
  // NAVIGATE TO VEHICLES
  goToVehicles(fleet){
    this.router.navigate(['/vehicles/'+fleet.fleetID,
      {companyID: this.companyid,companyName: this.companyName, fleetName:fleet.fleetName}]);
  }
  updateTable(data){
    this.dataSource = new MatTableDataSource<any>(data);
    this.dataSource.paginator = this.paginator;
  }

  //FILTER TABLE BY FLEET NAME
  doFilter(){
    var value = this.filterInput.nativeElement.value;
    if(value == ""){
      this.tableData = this.backupData;
    }
    else {
      this.tableData = this.tableData.filter((datum:any)=>{
        return datum.fleetName.startsWith(value);
      });
    }

    this.updateTable(this.tableData);

  }
}
