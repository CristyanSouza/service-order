import { AfterViewInit, Component } from '@angular/core';
import { Router } from '@angular/router';
import { Client } from 'src/app/models/Client-model';
import { ServiceOrder } from 'src/app/models/ServiceOrder-model';
import { Technician } from 'src/app/models/Technician-model';
import { ClientService } from 'src/app/services/client.service';
import { ServiceorderService } from 'src/app/services/serviceorder.service';
import { TechnicianService } from 'src/app/services/technician.service';

@Component({
  selector: 'app-serviceorder-create',
  templateUrl: './serviceorder-create.component.html',
  styleUrls: ['./serviceorder-create.component.css']
})
export class ServiceorderCreateComponent implements AfterViewInit {

  
  osModel : ServiceOrder = {
      priority : '',
      comments: '',
      status : '',
      technician : '',
      client : '',
      openingDate: '',
  
  }
  technicians : Technician[] = [];
  clients : Client[] = [];

  constructor(private technicianService : TechnicianService,
    private clientService : ClientService,
    private service : ServiceorderService,
    private router : Router
    ){
  }

  ngAfterViewInit(): void {
    this.listTechnicians();
    this.listClients();
  }


  create() : void {
    this.service.create(this.osModel)
      .then(() => {
        this.service.message("Ordem de serviço criada com sucesso");
        this.navigateToServicesOrder();
      })
  }

  listTechnicians() : void {
    this.technicianService.findAll().subscribe(response => {
      this.technicians = response
    })
  }

  listClients() : void {
    this.clientService.findAll().subscribe(response => {
      this.clients = response
    })
  }

  navigateToServicesOrder(){
    this.router.navigate(['/serviceorder']);
  }
}
