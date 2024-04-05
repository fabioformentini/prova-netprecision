export class PedidoProdutoListModel {

  private idPedido: number;
  private idProduto: number;
  private nome: number;
  private quantidade: number;


  constructor(idPedido: number, idProduto: number, nome: number, quantidade: number) {
    this.idPedido = idPedido;
    this.idProduto = idProduto;
    this.nome = nome;
    this.quantidade = quantidade;
  }

}
