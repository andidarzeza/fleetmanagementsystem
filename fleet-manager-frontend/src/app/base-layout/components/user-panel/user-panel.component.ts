import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
interface ILink {
  caption: string,
  info: string,
}
@Component({
  selector: 'user-panel',
  templateUrl: './user-panel.component.html',
  styleUrls: ['./user-panel.component.scss']
})

export class UserPanelComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }


  links:ILink[] = [{
    caption: 'Company One',
    info: 'Company one'
  },
    {
      caption: 'Company two',
      info: 'Company two',
    },
    {
      caption: 'Company three',
      info: 'Company three',
    },
    {
      caption:'Company four',
      info: 'Company four',
    },
  ]

  removeCompany(msg:ILink){
console.log(this.links);
    const index: number = this.links.indexOf(msg);
    if (index !== -1) {
      this.links.splice(index, 1);
    }
  }

  goToCompanyDetails(id){
    this.router.navigate(['/company-details',id]);

  }
}
