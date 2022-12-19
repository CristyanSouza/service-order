import { ThisReceiver } from '@angular/compiler';
import { AfterViewInit, Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from 'src/app/models/Client-model';
import { ServiceOrder } from 'src/app/models/ServiceOrder-model';
import { Technician } from 'src/app/models/Technician-model';
import { ClientService } from 'src/app/services/client.service';
import { ServiceorderService } from 'src/app/services/serviceorder.service';
import { TechnicianService } from 'src/app/services/technician.service';

@Component({
  selector: 'app-serviceorder-update',
  templateUrl: './serviceorder-update.component.html',
  styleUrls: ['./serviceorder-update.component.css']
})
export class ServiceorderUpdateComponent {


  soModel : ServiceOrder = {
      priority : '',
      comments: '',
      status : '',
      technician : '',
      client : '',
      openingDate: '',
  
  }


  soView : ServiceOrder = {
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
    private router : Router,
    private route : ActivatedRoute
    ){
  }


  ngOnInit(): void {
    this.soModel.id = this.route.snapshot.paramMap.get("id")
    this.soView.id = this.route.snapshot.paramMap.get("id")
    this.findById();
    this.listTechnicians();
    this.listClients();
  }


  findById() : void {
    this.service.findById(this.soModel.id)
      .then( response => {
         this.soModel = response.data;
      })


      this.service.findById(this.soView.id)
      .then( response => {
         this.soView = response.data;
         this.service.createView(this.soView);
      })
  }
 
  update() : void {
    this.service.update(this.soModel)
      .then(() => {
        this.service.message("Ordem de serviço atualizada com sucesso");
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
