import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import axios, { AxiosResponse } from 'axios';
import { Observable } from 'rxjs';
import { enviroment } from 'src/enviroment/enviroments';
import { ServiceOrder } from '../models/ServiceOrder-model';
import { ClientService } from './client.service';
import { TechnicianService } from './technician.service';

@Injectable({
  providedIn: 'root'
})
export class ServiceorderService {

  baseUrl: String = enviroment.BASEURL

  constructor(private http: HttpClient, private snack: MatSnackBar,
    private technicianService: TechnicianService, private clientService: ClientService) { }

  findAll(): Observable<ServiceOrder[]> {
    const url = this.baseUrl + '/serviceorder'

    return this.http.get<ServiceOrder[]>(url);
  }

  findById(id: any): Promise<AxiosResponse<ServiceOrder>> {
    const url = `${this.baseUrl}/serviceorder/${id}`
    return axios.get<ServiceOrder>(url);
  }

  create(serviceorder: ServiceOrder): Promise<AxiosResponse<ServiceOrder>> {
    const url = this.baseUrl + '/serviceorder'

    return axios.post<ServiceOrder>(url, serviceorder);
  }

  update(serviceorder: ServiceOrder): Promise<AxiosResponse<ServiceOrder>> {
    let so = serviceorder

    if (so.priority == 'LOW') {
      so.priority = 0
    }
    if (so.priority == 'MEDIUM') {
      so.priority = 1
    }
    if (so.priority == 'HIGH') {
      so.priority = 2
    }

    if (so.status == 'OPENED') {
      so.status = 0
    }
    if (so.status == 'IN_PROGRESS') {
      so.status = 1
    }
    if (so.status == 'CLOSED') {
      so.status = 2
    }

    const url = `${this.baseUrl}/serviceorder`
    return axios.put<ServiceOrder>(url, serviceorder);
  }


  delete(serviceorder: ServiceOrder): Promise<AxiosResponse<ServiceOrder>> {
    const url = `${this.baseUrl}/serviceorder/${serviceorder.id}`
    return axios.delete<ServiceOrder>(url);
  }

  createView(so: ServiceOrder): ServiceOrder {
    var soView = so;
    //Priority
    if (so.priority == "LOW") {
      soView.priority = "BAIXA";
    }
    if (so.priority == "MEDIUM") {
      soView.priority = "MEDIA";
    }
    if (so.priority == "HIGH") {
      soView.priority = "ALTA";
    }

    //Status
    if (so.status == "OPENED") {
      soView.status = "ABERTO"
    }
    if (so.status == "IN_PROGRESS") {
      soView.status = "EM ANDAMENTO"
    }
    if (so.status == "CLOSED") {
      soView.status = "ENCERRADO"
    }

    //Technician
    this.technicianService.findById(so.technician)
      .then(response => {
        soView.technician = response.data.name
      })

    //Client
    this.clientService.findById(so.client)
      .then(response => {
        soView.client = response.data.name
      })


    return soView;
  }

  message(msg: String): void {
    this.snack.open(`${msg}`, 'OK', {
      horizontalPosition: 'end',
      verticalPosition: 'top',
      duration: 4000

    })
  }
}