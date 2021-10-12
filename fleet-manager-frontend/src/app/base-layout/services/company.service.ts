import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
const APIUrl = environment.APIUrl;
@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private http: HttpClient) { }

  getAllCompaniesByUserID(userID) {
    return this.http.get(APIUrl+"/companies/all/" + userID);
  }

  getAllCompanies() {
    return this.http.get(APIUrl+ "/companies/all");
  }
  addCompany(username, companyName) {
    return this.http.post(APIUrl+ "/companies/add",
        {userID: username, companyName: companyName});
  }

  deleteCompany(id) {
    return this.http.delete(APIUrl+ "/companies/delete/"+id);
  }

  updateCompany(id,company) {
    return this.http.put(APIUrl +"/companies/update"+id,company);
  }

}
