export class PedidoModel {
  public codigo: string;
  public troco: number = 0;


  constructor(codigo: string) {
    this.codigo = codigo;
  }
}
