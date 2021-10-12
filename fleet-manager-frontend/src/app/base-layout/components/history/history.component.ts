import { Component, OnInit } from '@angular/core';
import {HistoryService} from "../../services/history.service";
import * as jwt_decode from 'jwt-decode';
import {CdkDragDrop, moveItemInArray} from "@angular/cdk/drag-drop";

@Component({
  selector: 'history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.scss']
})
export class HistoryComponent implements OnInit {

  histories:any = [];

  constructor(public historyService:HistoryService) { }
  ngOnInit() {
    var decode = jwt_decode(localStorage.getItem("token"));
    this.historyService.getAllHistoryByUserID(decode.sub).subscribe(res=>{
      console.log(res);
      this.histories = res;

    });
  }
  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.histories, event.previousIndex, event.currentIndex);
  }
}
