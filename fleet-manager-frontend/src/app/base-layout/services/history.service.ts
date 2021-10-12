import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import{ environment } from "../../../environments/environment";
const APIUrl = environment.APIUrl;
@Injectable({
  providedIn: 'root'
})
export class HistoryService {

  constructor(private http:HttpClient) { }

  getAllHistories(){
    return this.http.get(APIUrl+"/histories/all");
  }

  getAllHistoryByUserID(userID){
    return this.http.get(APIUrl+ "/histories/all/"+ userID);
  }


  deleteHistory(historyID){
    return this.http.delete(APIUrl+"/histories/delete/"+ historyID);

  }
}
