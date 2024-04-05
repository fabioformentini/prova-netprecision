import {environment} from "../../../environments/environment";
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

export abstract class AbstractService<T, Y> {
    protected constructor(protected http: HttpClient) { }

    resourceUrl = environment.apiUrl + '/' + this.getEntity();

    abstract getEntity(): string;

    insert(entity: T): Observable<T> {
        return this.http.post<T>(this.resourceUrl, entity);
    }

    findById(id: number): Observable<T> {
        return this.http.get<T>(this.resourceUrl + '/' + id);
    }

    findAll(): Observable<Y[]> {
        return this.http.get<Y[]>(this.resourceUrl);
    }

    update(entity: T): Observable<T> {
        return this.http.put<T>(this.resourceUrl, entity);
    }

    delete(id: number): Observable<void> {
        return this.http.delete<void>(this.resourceUrl + '/' + id);
    }

}
