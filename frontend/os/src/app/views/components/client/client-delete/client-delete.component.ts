import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from 'src/app/models/Client-model';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-client-delete',
  templateUrl: './client-delete.component.html',
  styleUrls: ['./client-delete.component.css']
})
export class ClientDeleteComponent {

  client_id : any;
  
  
 client : Client = {
   id: '',
   name : '',
   cpf : '',
   phoneNumber : ''
 }

 constructor(
   private router: Router, 
   private service : ClientService,
   private route : ActivatedRoute
   ){

 }

 ngOnInit(): void {
   this.client_id = this.route.snapshot.paramMap.get('id')

   this.service.findById(this.client_id)
     .then(response => {
       this.client = response.data;
     })
 }

 navigateToClients() : void {
   this.router.navigate(['/client']);
 }

 delete() : void {
  this.service.delete(this.client)
    .then(response => {
      response.status
      this.service.message("Cliente deletado com sucesso");
        })
      .catch(err => {
        if(err.response.data.error == "O cliente possui ordens de serviços vinculadas"){
          this.service.message("O cliente possui ordens de serviços vinculadas");
        }
      })
      .finally(() => {
        this.navigateToClients();
      }
      );
 }
}
