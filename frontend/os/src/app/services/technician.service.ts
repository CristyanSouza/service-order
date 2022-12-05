import { Injectable } from '@angular/core';
import { enviroment } from 'src/enviroment/enviroments';
import { Technician } from '../models/Technician-model';
import { endWith, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';

import axios, { AxiosResponse } from 'axios';

@Injectable({
  providedIn: 'root'
})
export class TechnicianService {

  baseUrl : String = enviroment.BASEURL

  constructor(private http : HttpClient, private snack : MatSnackBar) { }

  findAll():Observable<Technician[]> {
    const url = this.baseUrl + '/technician'

    return this.http.get<Technician[]>(url);
}

  findById(id : any):Promise<AxiosResponse<Technician>>{
    const url = `${this.baseUrl}/technician/${id}`
    return axios.get<Technician>(url);
  }

  create(technician : Technician):Promise<AxiosResponse<Technician>> {
    const url = this.baseUrl + '/technician'

    return axios.post<Technician>(url, technician);
  } 

  update(technician : Technician):Promise<AxiosResponse<Technician>> {
    const url = `${this.baseUrl}/technician/${technician.id}`
    return axios.put<Technician>(url, technician);
  } 


  delete(technician : Technician):Promise<AxiosResponse<Technician>> {
    const url = `${this.baseUrl}/technician/${technician.id}`
    return axios.delete<Technician>(url);
  } 

  message(msg : String) : void {
    this.snack.open(`${msg}`, 'OK', {
      horizontalPosition: 'end',
      verticalPosition: 'top',
      duration: 4000

    })
  }
}