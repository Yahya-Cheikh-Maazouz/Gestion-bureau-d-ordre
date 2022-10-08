import {Component, HostListener} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'service-gestion-courriers-bo-frontend';
  constructor() {

  }
  public mobile: boolean=true;
  displayAnim:boolean=false;

  ngOnInit() {
    this.displayAnim=true
    setTimeout(() => {
      this.displayAnim=false
    }, 2000); //
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
