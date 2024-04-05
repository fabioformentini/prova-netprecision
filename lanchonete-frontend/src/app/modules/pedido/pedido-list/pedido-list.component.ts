import {Component, OnInit} from '@angular/core';
import {Column} from "../../../shared/models/column.model";
import {PedidoService} from "../../../shared/services/pedido.service";
import {PedidoModel} from "../../../shared/models/pedido.model";
import {PedidoFormComponent} from "../pedido-form/pedido-form.component";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'app-pedido-form',
  templateUrl: './pedido-list.component.html',
  styleUrls: ['./pedido-list.component.scss'],
  providers: [DialogService, ConfirmationService, MessageService]

})
export class PedidoListComponent implements OnInit {

  ref: DynamicDialogRef | undefined;

  constructor(private service: PedidoService,
              public dialogService: DialogService,
              private messageService: MessageService,
              private confirmationService: ConfirmationService) {
    this.construirColunasListagem();
  }


  ngOnInit(): void {
    this.buscarPedidos();
  }

  pedidos: PedidoModel[] = [];
  cols!: Column[];

  private construirColunasListagem() {
    this.cols = [
      {field: 'codigo', header: 'Código', text: true},
      {field: 'valorPagamento', header: 'Valor Total', text: true},
      {field: 'pedidoFechado', header: 'Status', text: true},
      {field: 'acoes', header: 'Ações'}
    ];
  }

  criarPedido() {
    let codigo = Math.floor(100000 + Math.random() * 900000)
    let pedido = new PedidoModel(codigo.toString())
    this.service.insert(pedido).subscribe(value => {
      this.buscarPedidos();
    })
  }

  private buscarPedidos() {
    this.service.findAll().subscribe((value: any) => {
      this.pedidos = value;
    })
  }

  handleAcao(rowData: any, acao: string) {
    this.service.findById(rowData.id).subscribe((value) => {
      this.ref = this.dialogService.open(PedidoFormComponent,
        {
          header: 'Formulário Pedido',
          width: '60%',
          data: {pedido: value, acao: acao}
        });
      this.ref.onClose.subscribe((pedido) => {
        if (pedido) {
          this.messageService.add({
            severity: 'success',
            summary: 'Success',
            detail: 'O pedido ' + pedido.codigo + ' foi editado com sucesso!'
          })
          this.buscarPedidos()
        }
      });
    })


  }

  fecharPedido(id: number) {

  }
}
