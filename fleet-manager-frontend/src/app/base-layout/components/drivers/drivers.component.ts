import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatDialog} from "@angular/material/dialog";
import {DriversService} from "../../services/drivers.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ConfirmService} from "../../services/confirm.service";

@Component({
  selector: 'drivers',
  templateUrl: './drivers.component.html',
  styleUrls: ['./drivers.component.scss']
})
export class DriversComponent implements OnInit, OnDestroy {
  // VIEW CHILD TO OPEN POPUP FOR NEW DRIVER
  @ViewChild("newDriver") newDriver: any;
  driverGroup: FormGroup;
  dialogRef;
  drivers = [];
  //LOCATION OF DEFAULT IMAGE
  uploadedImage = 'assets/images/profile-photo.png';
  sub:any;
  fileToUpload: File = null;
  // USING FORM.DATA TO UPLOAD IMAGE
  formData:FormData = new FormData();
  private companyID = "";
  constructor(public formBuilder: FormBuilder, public dialog: MatDialog,
              public driverService:DriversService,public router:Router, private dialogService:ConfirmService,
              private activatedRoute: ActivatedRoute) {
  }
  ngOnInit() {
    this.sub = this.activatedRoute.paramMap.subscribe((param:any)=> {
      this.companyID = param.get('id');
      console.log("companyid is : ", this.companyID);
      this.driverGroup = this.formBuilder.group({
        companyID: [this.companyID],
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        driverAge: ['', Validators.required],
        driverAddress: ['', Validators.required],
        vehicleAssignedFrom:[null]
      });
      this.driverService.getAllDriversByCompanyID(this.companyID).subscribe((response:any) => {
        console.log(response);
        this.drivers = response;
      });
    });
  }
  ngOnDestroy(){
    this.sub.unsubscribe();
  }
  openPopUp() {
    this.dialogRef = this.dialog.open(this.newDriver, {
      width: '600px',
      height: '450px '
    });
  }
  closeModal() {
    if (this.dialogRef != null) {
      this.dialogRef.close();
      console.log('click');
    }
  }
  onFileChanged(event) {
    this.fileToUpload = event.target.files[0];
    let formData:FormData = new FormData();
    var reader = new FileReader();
    reader.onload = (event: any) => {
      this.uploadedImage = event.target.result;
    }
    reader.readAsDataURL(this.fileToUpload);
  }
  addDriver(){
    // USE APPEND METHOD FROM FORM.DATA TO STORE KEY-VALUE PAIRS
    this.formData.append("driver", JSON.stringify(this.driverGroup.value));
    console.log(this.driverGroup.value);
    this.formData.append('profilePhoto', this.fileToUpload);
    this.driverService.addDriver(this.formData).subscribe((response:any)=>{
      this.drivers.push(response);
    });
    this.dialogRef.close();
    this.driverGroup.reset();
  }
   deleteDriver(driver){
     this.dialogService.openDialog("Do you want to delete this Driver?").afterClosed().subscribe((res)=>{
       if (res) {
         this.driverService.deleteDriver(driver.driverID).subscribe((res: any) => {
           const index = this.drivers.indexOf(driver);
           if (index > -1) {
             this.drivers.splice(index, 1);
           }
         });
       }
   })};

   
}
