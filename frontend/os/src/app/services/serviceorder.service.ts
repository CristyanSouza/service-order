import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import axios, { AxiosResponse} from 'axios';
import { Observable } from 'rxjs';
import { enviroment } from 'src/enviroment/enviroments';
import { ServiceOrder } from '../models/ServiceOrder-model';

@Injectable({
  providedIn: 'root'
})
export class ServiceorderService {

  baseUrl : String = enviroment.BASEURL

  constructor(private http : HttpClient, private snack : MatSnackBar) { }

  findAll():Observable<ServiceOrder[]> {
    const url = this.baseUrl + '/serviceorder'

    return this.http.get<ServiceOrder[]>(url);
}

  findById(id : any):Promise<AxiosResponse<ServiceOrder>>{
    const url = `${this.baseUrl}/serviceorder/${id}`
    return axios.get<ServiceOrder>(url);
  }

  create(serviceorder : ServiceOrder):Promise<AxiosResponse<ServiceOrder>> {
    const url = this.baseUrl + '/serviceorder'

    return axios.post<ServiceOrder>(url, serviceorder);
  } 

  update(serviceorder : ServiceOrder):Promise<AxiosResponse<ServiceOrder>> {
    const url = `${this.baseUrl}/serviceorder/${serviceorder.id}`
    return axios.put<ServiceOrder>(url, serviceorder);
  } 


  delete(serviceorder : ServiceOrder):Promise<AxiosResponse<ServiceOrder>> {
    const url = `${this.baseUrl}/serviceorder/${serviceorder.id}`
    return axios.delete<ServiceOrder>(url);
  } 

  message(msg : String) : void {
    this.snack.open(`${msg}`, 'OK', {
      horizontalPosition: 'end',
      verticalPosition: 'top',
      duration: 4000

    })
  }
}