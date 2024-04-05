export class PedidoProdutoListModel {

  public idPedido: number;
  public idProduto: number;
  public nome: number;
  public quantidade: number;


  constructor(idPedido: number, idProduto: number, nome: number, quantidade: number) {
    this.idPedido = idPedido;
    this.idProduto = idProduto;
    this.nome = nome;
    this.quantidade = quantidade;
  }

}
