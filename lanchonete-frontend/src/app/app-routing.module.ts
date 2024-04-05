import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: '', pathMatch: 'prefix', redirectTo: 'pedidos'},
  { path: 'pedidos', loadChildren: () => import('./modules/pedido/pedido.module').then(pedido => pedido.PedidoModule)},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
