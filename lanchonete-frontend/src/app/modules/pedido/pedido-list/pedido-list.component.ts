import {Component, OnInit} from '@angular/core';
import {Column} from "../../../shared/models/column.model";
import {PedidoService} from "../../../shared/services/pedido.service";
import {PedidoModel} from "../../../shared/models/pedido.model";
import {PedidoFormComponent} from "../pedido-form/pedido-form.component";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService} from "primeng/api";
import {FecharPedidoModel} from "../../../shared/models/fechar-pedido.model";

@Component({
  selector: 'app-pedido-form',
  templateUrl: './pedido-list.component.html',
  styleUrls: ['./pedido-list.component.scss'],
  providers: [DialogService, ConfirmationService, MessageService]

})
export class PedidoListComponent implements OnInit {

  ref: DynamicDialogRef | undefined;
  displayModal: boolean = false;
  idPedido: number = 0;
  pedidos: PedidoModel[] = [];
  cols!: Column[];
  valorPagamento!: number;
  troco: number = 0;
  pedidoPago: boolean = false;

  constructor(private service: PedidoService,
              public dialogService: DialogService,
              private messageService: MessageService,
              private confirmationService: ConfirmationService) {
    this.construirColunasListagem();
  }


  ngOnInit(): void {
    this.buscarPedidos();
  }


  private construirColunasListagem() {
    this.cols = [
      {field: 'codigo', header: 'Código', text: true},
      {field: 'valorPagamento', header: 'Valor Pagamento'},
      {field: 'pedidoFechado', header: 'Status'},
      {field: 'acoes', header: 'Ações'}
    ];
  }

  criarPedido() {
    let codigo = Math.floor(100000 + Math.random() * 900000)
    let pedido = new PedidoModel(codigo.toString())
    this.service.insert(pedido).subscribe(value => {
      this.messageService.add({
        severity: 'success',
        summary: 'Success',
        detail: 'O pedido ' + value.codigo + ' foi criado com sucesso!'
      })
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
    })
  }

  fecharPedido(id: number) {
    this.idPedido = id;
    this.displayModal = true;
    this.troco = 0;
    this.valorPagamento = 0;
  }

  fechar() {
    const fecharPedidoModel: FecharPedidoModel = new FecharPedidoModel(this.idPedido, this.valorPagamento);
    this.service.fecharPedido(fecharPedidoModel).subscribe(value => {
      this.troco = value.troco
      this.pedidoPago = true;
      this.messageService.add({
        severity: 'success',
        summary: 'Success',
        detail: 'O pedido ' + value.codigo + ' foi finalizado!'
      })
      this.buscarPedidos();
    })
  }

}
