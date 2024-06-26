import {Injectable} from '@angular/core';
import {AbstractService} from "./abstract.service";
import {HttpClient} from "@angular/common/http";
import {PedidoModel} from "../models/pedido.model";
import {PedidoListModel} from "../models/pedido-list.model";
import {Observable} from "rxjs";
import {PedidoProdutoModel} from "../models/pedido-produto.model";
import {PedidoProdutoListModel} from "../models/pedido-produto-list.model";
import {TotalPedidoModel} from "../models/total-pedido.model";
import {FecharPedidoModel} from "../models/fechar-pedido.model";

@Injectable({
    providedIn: 'root'
})
export class PedidoService extends AbstractService<PedidoModel, PedidoListModel> {

    constructor(private httpClient: HttpClient) {
        super(httpClient);
    }

    override getEntity(): string {
        return "pedidos";
    }

  adicionarProduto(pedidoProduto: PedidoProdutoModel): Observable<PedidoProdutoModel> {
    return this.http.post<PedidoProdutoModel>(this.resourceUrl + '/adicionar-produto', pedidoProduto)
  }

  buscarProdutosPedidosById(idPedido: number): Observable<PedidoProdutoListModel[]> {
    return this.http.get<PedidoProdutoListModel[]>(this.resourceUrl + '/buscar-produtos-pedido' + '/' + idPedido)
  }

  removerProduto(pedidoProduto: PedidoProdutoModel): Observable<PedidoProdutoModel> {
    return this.http.put<PedidoProdutoModel>(this.resourceUrl + '/remover-produto', pedidoProduto)
  }

  calcularTotalPedidoById(idPedido: number): Observable<number>{
      return this.http.get<number>(this.resourceUrl + '/calcular-preco-total' + '/' + idPedido)
  }

  calcularTotalPedidoByItens(totalPedido: TotalPedidoModel): Observable<number> {
      return this.http.post<number>(this.resourceUrl + '/calcular-preco-total', totalPedido)
  }

  fecharPedido(fecharPedidoModel: FecharPedidoModel): Observable<PedidoModel>{
      return this.http.post<PedidoModel>(this.resourceUrl + '/fechar-pedido', fecharPedidoModel)
  }

}
