export class PedidoProdutoModel {

  public idPedido: number;
  public idProduto: number;
  public quantidade: number;

  constructor(idPedido: number, idProduto: number, quantidade: number) {
    this.idPedido = idPedido;
    this.idProduto = idProduto;
    this.quantidade = quantidade;
  }

}
