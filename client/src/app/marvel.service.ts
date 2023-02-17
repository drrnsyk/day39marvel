import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, map, take } from "rxjs";
import { Character } from "./model";

@Injectable()
export class MarvelService {

    constructor(private http: HttpClient) {}

    /* the service is use to make http request, the data from the request can be pass 
    from the service to any of the component. just use the component that you want the data
    to be in to call the service. if search is the entry point, use navigate to get another component
    to call service so that the data can be passed into that component */

    // http can return an observable<Character> or a promise<Character>
    // observable: do not need to use firstValueFrom, straight away return this.http.get
    // observable: int the ts file, subscribe to the observable
    // promise: use firstValueFrom and pipe the data if needed, if it is a nested json
    // promise: since this is already a jsonarray string, can cast it directly to a list of object
    // promise: in the ts file, just use .then => and allocate the result to the list
    getCharacters(searchTerm: string, limit: number, offset: number): Promise<Character[]> {
        const params = new HttpParams()
            .set("searchTerm", searchTerm)
            .set("limit", limit)
            .set("offset", offset)

        return firstValueFrom<Character[]>(
            this.http.get<Character[]>('/api/characters', { params })
        )
    }
}
