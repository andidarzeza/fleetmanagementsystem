import {Component, OnInit, ViewChild} from '@angular/core';
import {CompanyService} from "../../services/company.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import * as jwt_decode from 'jwt-decode';
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";
import {ConfirmService} from "../../services/confirm.service";
@Component({
  selector: 'companies',
  templateUrl: './companies.component.html',
  styleUrls: ['./companies.component.scss']
})
export class CompaniesComponent implements OnInit{
private tableData: any = [];
private backupData: any = [];
public companyGroup:FormGroup;
public dialogRef;
public dataSource: any = [];
// TABLE COLUMNS
displayedColumns = ['user','CompanyName','options'];
@ViewChild("filter") filterInput:any;
//VIEW CHILD FOR ADDING NEW COMPANY POPUP
@ViewChild("newCompany") newCompany:any;
//VIEW CHILD FOR PAGINATOR
@ViewChild(MatPaginator) paginator: MatPaginator;
//VIEW CHILD FOR DELETE CONFIRMATION
@ViewChild("popupConfirm") popupConfirm:any;

  constructor(public formBuilder:FormBuilder, public router:Router,
              public companyService: CompanyService,
              public dialogService: ConfirmService,
              public dialog:MatDialog) {
    this.companyGroup = this.formBuilder.group({
      companyName:['',Validators.required],
    });
  }

   ngOnInit(): void {
    var decode = jwt_decode(localStorage.getItem("token"));
    this.companyService.getAllCompaniesByUserID(decode.sub).subscribe((data: any) => {
        this.tableData = data;
        this.backupData = data;
        this.updateTable(this.tableData);
    });
   }

   updateTable(data){
       this.dataSource = new MatTableDataSource<any>(data);
       this.dataSource.paginator = this.paginator;
   }

   // OPEN POPUP FOR ADDING COMPANY
  openPopUp() {
    this.dialogRef = this.dialog.open(this.newCompany, {
      width: '300px',
    });
  }
// CLOSE MODAL FOR ADDING COMPANY
  closeModal() {
    if (this.dialogRef != null) {
      this.dialogRef.close();
      console.log('click');
    }
  }
  // ADD COMPANY
  addCompany(){
        var decoded = jwt_decode(localStorage.getItem("token"));
        this.companyService.addCompany(decoded.sub, this.companyGroup.value.companyName).subscribe(
            (company:any) => {
                this.tableData.push(company);
                this.updateTable(this.tableData);
                this.dataSource.paginator.pageIndex = this.dataSource.paginator.pageSize;
            }
        );

        this.dialogRef.close();
        this.companyGroup.reset();
    }
// DELETE COMPANY AFTER CLICKING YES ON CONFIRMATION MODAL
//using  dialogService
  deleteCompany(company) {
      this.dialogService.openDialog("Do you want to delete this company?").afterClosed().subscribe((res)=>{
         if (res){
             this.companyService.deleteCompany(company.companyID).subscribe((response:any)=>{
                 const index = this.tableData.indexOf(company);
                 if (index > -1) {
                     this.tableData.splice(index, 1);
                     this.updateTable(this.tableData);
                 }
             });
         }
      });
  }

  //NAVIGATE TO FLEETS
  goToCompany(company){
      this.router.navigate(["/companies/" + company.companyID, {companyName: company.companyName}]);
  }


  // FILTER METHOD FOR FILTERING TABLE BY COMPANY NAME
  doFilter(){
      var value = this.filterInput.nativeElement.value;
      if(value == ""){
          this.tableData = this.backupData;
      }
      else {
          this.tableData = this.tableData.filter((datum:any)=>{
              return datum.companyName.startsWith(value);
          });
      }
      this.updateTable(this.tableData);
  }
}
