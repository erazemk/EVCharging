import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

//import { NakupovalniSeznam } from '../models/seznam';
import { Owner } from '../models/owner'
import { Observable } from 'rxjs'

import { catchError } from 'rxjs/operators';
//import { Artikel } from '../models/artikel';
import { Station } from '../models/station'
import {HttpClientModule} from '@angular/common/http';

@Injectable()
export class UserService {

    private url = 'http://prpo.erazem.eu/v1/stations';
    private url2 = 'http://prpo.erazem.eu/v1/owners';
    private url4 = 'http://prpo.erazem.eu/v1/locations'
    private url3 = 'http://do.erazem.eu:8080/auth/realms/evcharging/protocol/openid-connect/token';
    private apiKey = 'eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ5Ny1ETW5PYkFmc085ZkJVWWxvYW5mR0d6R3k4NV81NlR3RkNzNXVQWWpNIn0.eyJleHAiOjE2NDExNTgwMTUsImlhdCI6MTY0MTE1NzcxNSwianRpIjoiYTE1NDU4ZmItMDBkYS00NTI5LTg3NzgtM2Y0ZTRmNjA2OWJiIiwiaXNzIjoiaHR0cDovL2RvLmVyYXplbS5ldTo4MDgwL2F1dGgvcmVhbG1zL2V2Y2hhcmdpbmciLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiY2UyMGRiMDMtZDEwYi00ZWNiLTlhMmUtZWE2ZjZlZTJmNWZjIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiZXZjaGFyZ2luZy1hcHAiLCJzZXNzaW9uX3N0YXRlIjoiMDYzMTRmZjktNmQzOC00NjdlLWFjNDAtNjYzY2JiNjViMTliIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsImFkbWluIiwidW1hX2F1dGhvcml6YXRpb24iLCJkZWZhdWx0LXJvbGVzLWV2Y2hhcmdpbmciXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJzaWQiOiIwNjMxNGZmOS02ZDM4LTQ2N2UtYWM0MC02NjNjYmI2NWIxOWIiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6Im1pY3Jvc2VydmljZSJ9.ltCnwThu_L2t85Jf_v_QunsQdh-Qdvty94g-5u_qdfrdyyx2uT_7rEVSPG_b9GAQRNveqrBgRQGVaRlZdjQ65eaQ6EZlVOWpSnJJM5LxbMHmjf6xRSjc-bRJRK7sGpOEUsP7xgc0M-RSxFRh-yYsSJ6htdAWZStyGwf1anycYZB-cF3Wvfg1Kliu4j4Jm1JmHf5UgLrnHoDcQ6BoxbuXB6eWXBP76zOGESxLCK3YKp4bzj1SjuW1D_s-WwS0jsCJWD1_GmlGB8OCUk1Yi7kkFuQOLcIR4gHVmocFp-9ecqof946IjeU8JSOrLoCM-vIMqBeYj6qGXKAwlDs_Nfqplg'
    private headers = new HttpHeaders()
        .set('Content-Type','application/json')
        .set('Authorization','Bearer ' + this.apiKey)

    constructor(private http: HttpClient) {
    }

    getStations(): Observable<Station[]> {
        return this.http.get<Station[]>(this.url, {headers: this.headers})
                        .pipe(catchError(this.handleError));
    }

    getStation(id: number): Observable<Station> {
        const url = `${this.url}/${id}`
        return this.http.get<Station>(url, {headers: this.headers})
                        .pipe(catchError(this.handleError));
    }

    getOwner(id: number): Observable<Owner> {
        const url = `${this.url2}/${id}`
        return this.http.get<Owner>(url, {headers: this.headers})
                        .pipe(catchError(this.handleError));
    }

    delete(id: number): Observable<number> {
        const url = `${this.url}/${id}`
        return this.http.delete<number>(url, {headers: this.headers})
                        .pipe(catchError(this.handleError));

    }

    create(station: Station): Observable<Station> {
        return this.http.post<Station>(this.url, JSON.stringify(station), {headers: this.headers})
    }

    private handleError(error: any): Promise<any> {
        console.error('Prišlo je do napake', error);
        return Promise.reject(error.message || error);
    }
}
