import { Injectable } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {ConfirmDialogComponent} from "../components/confirm-dialog/confirm-dialog.component";

@Injectable({
  providedIn: 'root'
})
export class ConfirmService {
  constructor(private dialog: MatDialog) { }

  public openDialog(message: string) {
    return this.dialog.open(ConfirmDialogComponent, {
      panelClass: 'confirm-dialog-class',
      disableClose: true,
      width: '400px',
      data: message
    });
  }
}
