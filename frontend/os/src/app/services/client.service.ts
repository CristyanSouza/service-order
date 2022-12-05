import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import axios, { AxiosResponse } from 'axios';
import { Observable } from 'rxjs';
import { enviroment } from 'src/enviroment/enviroments';
import { Client } from '../models/Client-model';
import { Technician } from '../models/Technician-model';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  baseUrl : String = enviroment.BASEURL

  constructor(private http : HttpClient, private snack : MatSnackBar) { }

  findAll():Observable<Client[]> {
    const url = this.baseUrl + '/client'

    return this.http.get<Client[]>(url);
}

  message(msg : String) : void {
    this.snack.open(`${msg}`, 'OK', {
      horizontalPosition: 'end',
      verticalPosition: 'top',
      duration: 4000

    })
  }}
