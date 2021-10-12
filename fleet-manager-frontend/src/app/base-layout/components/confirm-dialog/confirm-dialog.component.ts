import {Component, Inject, OnInit, Optional} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.scss']
})
export class ConfirmDialogComponent implements OnInit {

  constructor(private ref: MatDialogRef<ConfirmDialogComponent>,
              @Optional() @Inject(MAT_DIALOG_DATA) private data) { }
  private message = '';
  ngOnInit() {
    this.message = this.data;
  }

  closeDialog() {
    this.ref.close(false);
  }

}
