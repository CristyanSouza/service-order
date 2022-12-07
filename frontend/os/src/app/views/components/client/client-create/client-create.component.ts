import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Client } from 'src/app/models/Client-model';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-client-create',
  templateUrl: './client-create.component.html',
  styleUrls: ['./client-create.component.css']
})
export class ClientCreateComponent {

  client : Client = {
    name : '',
    cpf : '',
    phoneNumber : ''
  }

  name = new FormControl('', Validators.minLength(5));
  cpf = new FormControl('', Validators.minLength(11));
  phoneNumber = new FormControl('', Validators.minLength(11));

  constructor(private router: Router, private service : ClientService){

  }


  navigateToClients() : void {
    this.router.navigate(['/client']);
  }

  create() : void {
    this.service.create(this.client)
      .then(() => {
      this.navigateToClients();
      this.service.message("Cliente criado com sucesso")
      
    })
    .catch(error => {
      if (error.response.data.error == "CPF já cadastrado no sistema"){
      this.service.message("CPF já cadastrado no sistema")
    }else if (error.response.data.listErrors[0].message == "número do registro de contribuinte individual brasileiro (CPF) inválido"){
      this.service.message("CPF inválido")
    }
    });

  }

}
