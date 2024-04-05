export class FecharPedidoModel {
  constructor(id: number, valorPagamento: number) {
    this.id = id;
    this.valorPagamento = valorPagamento;
  }

  public id: number;
  public valorPagamento: number;

}
