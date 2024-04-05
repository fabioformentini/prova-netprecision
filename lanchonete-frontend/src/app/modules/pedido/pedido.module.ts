import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {PedidoRoutingModule} from './pedido-routing.module';
import {PedidoListComponent} from './pedido-list/pedido-list.component';
import {ButtonModule} from "primeng/button";
import {TableModule} from "primeng/table";
import { PedidoFormComponent } from './pedido-form/pedido-form.component';
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {ToastModule} from "primeng/toast";
import {DropdownModule} from "primeng/dropdown";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputNumberModule} from "primeng/inputnumber";
import {DialogModule} from "primeng/dialog";
import {SharedModule} from "../../shared/shared.module";


@NgModule({
  declarations: [
    PedidoListComponent,
    PedidoFormComponent
  ],
  imports: [
    CommonModule,
    PedidoRoutingModule,
    ButtonModule,
    TableModule,
    ConfirmDialogModule,
    ToastModule,
    DropdownModule,
    ReactiveFormsModule,
    InputNumberModule,
    DialogModule,
    FormsModule,
    SharedModule
  ]
})
export class PedidoModule {
}
