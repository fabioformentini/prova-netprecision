import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MessageService, SelectItem} from "primeng/api";
import {DialogService, DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {PedidoService} from "../../../shared/services/pedido.service";
import {ProdutoService} from "../../../shared/services/produto.service";
import {Column} from "../../../shared/models/column.model";
import {PedidoProdutoModel} from "../../../shared/models/pedido-produto.model";
import {PedidoProdutoListModel} from "../../../shared/models/pedido-produto-list.model";
import {TotalPedidoModel} from "../../../shared/models/total-pedido.model";
import {ProdutoQuantidadeModel} from "../../../shared/models/produto-quantidade.model";

@Component({
  selector: 'app-pedido-form',
  templateUrl: './pedido-form.component.html',
  styleUrls: ['./pedido-form.component.scss']
})
export class PedidoFormComponent implements OnInit {

  formPedido: FormGroup;
  isEdit: boolean = false;
  isVisualizar: boolean = false;
  produtosOptions: SelectItem[] | undefined;
  colsProdutos!: Column[];
  produtosList: PedidoProdutoListModel[] = [];
  valorTotalPedido: number = 0.00;

  constructor(
    private messageService: MessageService,
    public dialogService: DialogService,
    private dialogConfig: DynamicDialogConfig,
    private service: PedidoService,
    private produtoService: ProdutoService,
    public ref: DynamicDialogRef,
    private fb: FormBuilder) {
    this.formPedido = this.definirFormularioPedido()
    this.construirColunasListagem();
  }

  ngOnInit(): void {
    this.verificarAcao();
    this.buscarProdutos();
  }

  private definirFormularioPedido(): FormGroup {
    this.formPedido = this.fb.group({
      idPedido: [null, [Validators.required]],
      idProduto: [null, [Validators.required]],
      quantidade: [null, [Validators.required]],
    });
    return this.formPedido
  }

  private verificarAcao() {
    if (this.dialogConfig.data.acao == 'visualizar') {
      this.formPedido.disable();
      this.isVisualizar = true;
    }
    if (this.dialogConfig.data.acao == 'editar') {
      this.formPedido.enable();
      this.isEdit = true;
    }
    this.renderizarDadosPedido();
  }

  private renderizarDadosPedido() {
    const pedidoEncontrado = this.dialogConfig.data.pedido;
    if (!pedidoEncontrado) {
      return;
    }
    this.formPedido.get('idPedido')?.setValue(pedidoEncontrado.id)
    this.buscarProdutosPedido(pedidoEncontrado.id);
    this.calcularTotalPedido(pedidoEncontrado.id);
  }

  private buscarProdutos() {
    this.produtoService.buscarProdutosDropdown().subscribe(value => {
      this.produtosOptions = value;
    })
  }

  private construirColunasListagem() {
    this.colsProdutos = [
      {field: 'idProduto', header: 'Código', text: true},
      {field: 'nome', header: 'Produto', text: true},
      {field: 'quantidade', header: 'Quantidade', text: true},
      {field: 'acoes', header: 'Ações'}
    ];
  }


  removerProduto(rowData: PedidoProdutoModel) {
    const idPedido = rowData.idPedido;
    this.service.removerProduto(rowData).subscribe(value => {
      this.buscarProdutosPedido(idPedido);
    })

  }

  adicionarProduto() {
    let novoProduto: PedidoProdutoModel = this.formPedido.getRawValue();
    this.service.adicionarProduto(novoProduto).subscribe(value => {
      this.buscarProdutosPedido(value.idPedido)
    })
  }

  private buscarProdutosPedido(idPedido: number) {
    this.service.buscarProdutosPedidosById(idPedido).subscribe(value => {
      this.produtosList = value;
      this.calcularTotalPedidoByList(this.produtosList)
    })
  }

  private calcularTotalPedido(idPedido: number){
    this.service.calcularTotalPedidoById(idPedido).subscribe(value => {
      this.valorTotalPedido = value;
    })
  }

  private calcularTotalPedidoByList(produtosList: PedidoProdutoListModel[]) {
    let produtoQuantidadeModelList: ProdutoQuantidadeModel[] = [];
    produtosList.forEach(value => {
      const produtoQuantidadeModel: ProdutoQuantidadeModel = new ProdutoQuantidadeModel();
      produtoQuantidadeModel.idProduto = value.idProduto;
      produtoQuantidadeModel.quantidade = value.quantidade;
      produtoQuantidadeModelList.push(produtoQuantidadeModel)
    })
    let totalPedidoModel: TotalPedidoModel = new TotalPedidoModel(produtosList[0].idPedido, produtoQuantidadeModelList );
    this.service.calcularTotalPedidoByItens(totalPedidoModel).subscribe(value => {
      this.valorTotalPedido = value;
    })

  }

}
