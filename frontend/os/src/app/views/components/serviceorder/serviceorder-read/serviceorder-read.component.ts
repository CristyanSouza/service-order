import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ServiceOrder } from 'src/app/models/ServiceOrder-model';
import { ClientService } from 'src/app/services/client.service';
import { ServiceorderService } from 'src/app/services/serviceorder.service';
import { TechnicianService } from 'src/app/services/technician.service';

@Component({
  selector: 'app-serviceorder-read',
  templateUrl: './serviceorder-read.component.html',
  styleUrls: ['./serviceorder-read.component.css']
})
export class ServiceorderReadComponent implements AfterViewInit {

  constructor(private service : ServiceorderService, private router : Router, 
    private technicianService : TechnicianService,
    private clientService : ClientService){
  }

  serviceorders: ServiceOrder[] = [];


  displayedColumns: string[] = ['openingDate', 'client', 'technician', 'status', 'priority', 'closingDate', 'action' ];
  dataSource = new MatTableDataSource<ServiceOrder>(this.serviceorders);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngAfterViewInit() {
    this.findAll();
  }

  findAll(): void {
    this.service.findAll().subscribe((response) => {
      this.serviceorders = response;
      this.listTechnicians()
      this.listClients()
      this.setPriority()
      this.setStatus()
      this.dataSource = new MatTableDataSource<ServiceOrder>(this.serviceorders);
      this.dataSource.paginator = this.paginator;
    })
    }

    listTechnicians(): void {
      this.serviceorders.forEach(so => {
        this.technicianService.findById(so.technician)
          .then(response => {
            so.technician = response.data.name;
          })
      })
    }

    listClients(): void {
      this.serviceorders.forEach(so => {
        this.clientService.findById(so.client)
          .then(response => {
            so.client = response.data.name;
          })
      })
    }

    navigateToCreate() : void {
      this.router.navigate(['serviceorder/create']);
    }
    
    setPriority(): void {
      this.serviceorders.forEach(so => {
        if(so.priority == 'LOW') {
          so.priority = 'BAIXA'
        }
        if(so.priority == 'MEDIUM') {
          so.priority = 'MÉDIA'
        }
        if(so.priority == 'HIGH') {
          so.priority = 'ALTA'
        } 
      })
    }

    setStatus(): void {
      this.serviceorders.forEach(so => {
        if(so.status == 'OPENED') {
          so.status = 'EM ABERTO'
        }
        if(so.status == 'IN_PROGRESS') {
          so.status = 'EM ANDAMENTO'
        }
        if(so.status == 'CLOSED') {
          so.status = 'FECHADA'
        } 
      })
    }


  }

