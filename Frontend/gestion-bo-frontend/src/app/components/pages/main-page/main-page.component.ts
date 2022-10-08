import {Component, HostListener, OnInit} from '@angular/core';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {

  constructor() {

  }
  public mobile: boolean=true;
  ngOnInit() {
    if(innerWidth<1190)
      this.mobile=false
  }

  @HostListener('window:resize', ['$event'])
  onResize(event?:any) {
    // alert(event.target.innerWidth)
    if(event.target.innerWidth<1190)
      this.mobile=false
    else
      this.mobile=true
  }

}
