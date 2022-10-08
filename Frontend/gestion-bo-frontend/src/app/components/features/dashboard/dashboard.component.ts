import {Component, OnInit, ViewChild} from '@angular/core';
import {
  Chart,
  ArcElement,
  LineElement,
  BarElement,
  PointElement,
  BarController,
  BubbleController,
  DoughnutController,
  LineController,
  PieController,
  PolarAreaController,
  RadarController,
  ScatterController,
  CategoryScale,
  LinearScale,
  LogarithmicScale,
  RadialLinearScale,
  TimeScale,
  TimeSeriesScale,
  Decimation,
  Filler,
  Legend,
  Title,
  Tooltip,
  SubTitle
} from 'chart.js';
import {UserService} from "../../../core/services/user.service";
import {MatPaginator} from "@angular/material/paginator";
import {UserModel} from "../../../core/models/User.model";
import {MatTableDataSource} from "@angular/material/table";
import {DashboardService} from "../../../core/services/dashboard.service";

Chart.register(
  ArcElement,
  LineElement,
  BarElement,
  PointElement,
  BarController,
  BubbleController,
  DoughnutController,
  LineController,
  PieController,
  PolarAreaController,
  RadarController,
  ScatterController,
  CategoryScale,
  LinearScale,
  LogarithmicScale,
  RadialLinearScale,
  TimeScale,
  TimeSeriesScale,
  Decimation,
  Filler,
  Legend,
  Title,
  Tooltip,
  SubTitle
);
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  displayedColumns: string[] = ['firstName', 'lastName','email','Action'];
  dataSource :any
  displayed :boolean=false

  private chart1: any;
  private chart2: any;

  obj=[
    /*{
      title:"Nombre d'utilisateur",
      color: "rgba(255,137,1,0.7)",
      classIcon:"bi bi-person text-white",
      value:123
    },*/
    {
      title:"Total de Courrier",
      color: "rgba(54,206,159,0.7)",
      classIcon:"bi bi-pie-chart-fill text-white",
      value:234
    },
    {
      title:"Courrier Sortant",
      color: "rgba(102,111,228,0.7)",
      classIcon:"bi bi-bar-chart-line text-white",
      value:899
    },
    {
      title:"Courrier Entrant",
      color: "rgba(245,85,65,0.7)",
      classIcon:"bi bi-inboxes text-white",
      value:2398
    },

  ]




  constructor(private api: DashboardService) {

  }

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  private ListCourrier = new Array<UserModel>();
  public fileURL!: any;



  applyFilter($event: KeyboardEvent) {

  }








  ngOnInit(): void {
    this.api.getNombreTotalCourrier().subscribe((res:any)=>{
      this.obj[0].value=res.statisticValue
    })
    this.api.getNombreCourrier("Sortant").subscribe((res:any)=>{
      this.obj[1].value=res.statisticValue
    })
    this.api.getNombreCourrier("Entrant").subscribe((res:any)=>{
      this.obj[2].value=res.statisticValue
    })
    this.plotDashobord()
  }



  plotDashobord(){
    /* FIRST CHART */
    const dataChart1 : any= {
      labels: [],
      datasets: [{
        label: "", fill: true, backgroundColor: ['RGBA(54,193,235,0.27)', 'RGBA(255,206,86,0.24)', 'RGBA(255,99,132,0.23)', 'RGBA(165,216,221,0.21)', 'RGBA(204,101,254,0.22)', 'RGBA(234,106,71,0.17)'],
        borderWidth: 2, borderDash: [], borderDashOffset: 0.0,
        data: []
      }]
    }

    const configChart1 :any= {
      type: 'bar', data: dataChart1,
      options: {
        layout: {padding: {bottom: 25} }, interaction: {intersect: false},
        plugins: {
          tooltip: {
            enabled: true,
            callbacks: {
              footer: (ttItem : any) => {let sum = 0;let dataArr = ttItem[0].dataset.data;dataArr.map((data: any) => {sum += Number(data);});let percentage = (ttItem[0].parsed * 100 / sum).toFixed(2) + '%';
                return `Percentage : ${percentage}`;} } ,
          },datalabels: {formatter: (value :any, dnct1:any) => {let sum = 0;let dataArr = dnct1.chart.data.datasets[0].data;dataArr.map((data: any)  => {sum += Number(data);}); let percentage = (value * 100 / sum).toFixed(2) + '%';
              return percentage; }, color: '#000000',} ,legend: { align:"center", display: true,position:'bottom'
          }
        },
      }
    };

   this.api.getCourrierBystate().subscribe((res:any)=>{
     const Labels:any=[]
     const datas:any=[]
     res.data.map((item:any)=>{
        Labels.push(item.label);
        datas.push(item.statisticValue);
     })
     dataChart1.labels = Labels

     dataChart1.datasets[0].data = datas;
     if(!this.chart1)
     this.chart1 = new Chart('myChart1', configChart1);
   })




    /* SECOND CHART */
    const dataChart2 : any= {
      labels: [],
      datasets: [{
        label: "", fill: true, backgroundColor: ['RGBA(54,193,235,0.27)', 'RGBA(255,206,86,0.24)', 'RGBA(255,99,132,0.23)', 'RGBA(165,216,221,0.21)', 'RGBA(204,101,254,0.22)', 'RGBA(234,106,71,0.17)'],
        borderWidth: 2, borderDash: [], borderDashOffset: 0.0,
        data: []
      }]
    }

    const configChart2 :any= {
      type: 'doughnut', data: dataChart2,
      options: {
        layout: {padding: {bottom: 25} }, interaction: {intersect: false},
        plugins: {
          tooltip: {
            enabled: true,
            callbacks: {
              footer: (ttItem : any) => {let sum = 0;let dataArr = ttItem[0].dataset.data;dataArr.map((data: any) => {sum += Number(data);});let percentage = (ttItem[0].parsed * 100 / sum).toFixed(2) + '%';
                return `Percentage : ${percentage}`;} } ,
          },datalabels: {formatter: (value :any, dnct1:any) => {let sum = 0;let dataArr = dnct1.chart.data.datasets[0].data;dataArr.map((data: any)  => {sum += Number(data);}); let percentage = (value * 100 / sum).toFixed(2) + '%';
              return percentage; }, color: '#000000',} ,legend: { align:"center", display: true,position:'bottom'
          }
        },
      }
    };

    const labels: any = [
      'January',
      'February',
      'March',
      'April',
      'May',
      'June',
    ];
    this.api.getCourrierPourcentage().subscribe((res:any)=>{
      const Labels:any=[]
      const datas:any=[]
      res.data.map((item:any)=>{
        Labels.push(item.label);
        datas.push(item.statisticValue);
      })
      dataChart2.labels = Labels

      dataChart2.datasets[0].data = datas;
      if(!this.chart2)
      this.chart2 = new Chart('myChart', configChart2);
    })
  }








}
