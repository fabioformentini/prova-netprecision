import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PedidoListComponent} from "./pedido-list/pedido-list.component";

const routes: Routes = [
  {path: '', component: PedidoListComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PedidoRoutingModule { }
