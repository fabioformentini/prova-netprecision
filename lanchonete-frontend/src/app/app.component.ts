import {Component, OnInit} from '@angular/core';
import {MenuItem} from "primeng/api";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  items: MenuItem[] = []

  ngOnInit(): void {
    this.items =  [
      { label: 'Home', icon: 'pi pi-fw pi-home'},
      { label: 'Pedidos', icon: 'pi pi-fw pi-calendar', routerLinkActiveOptions: true, routerLink: "/ṕedidos" }
    ];
  }


}
