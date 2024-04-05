import {ProdutoQuantidadeModel} from "./produto-quantidade.model";

export class TotalPedidoModel {

  public id: number;
  public produtoQuantidadeDTOList: ProdutoQuantidadeModel[];

  constructor(id: number, produtoQuantidadeDTOList: ProdutoQuantidadeModel[]) {
    this.id = id;
    this.produtoQuantidadeDTOList = produtoQuantidadeDTOList;
  }

}
