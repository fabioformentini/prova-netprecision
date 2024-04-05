import {Injectable} from '@angular/core';
import {AbstractService} from "./abstract.service";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SelectItem} from "primeng/api";
import {ProdutoModel} from "../models/produto.model";
import {ProdutoListModel} from "../models/produto-list.model";

@Injectable({
    providedIn: 'root'
})
export class ProdutoService extends AbstractService<ProdutoModel, ProdutoListModel> {

    constructor(private httpClient: HttpClient) {
        super(httpClient);
    }

    override getEntity(): string {
        return "produtos";
    }

  buscarProdutosDropdown(): Observable<SelectItem[]> {
    return this.http.get<SelectItem[]>(this.resourceUrl + '/dropdown')
  }

}
