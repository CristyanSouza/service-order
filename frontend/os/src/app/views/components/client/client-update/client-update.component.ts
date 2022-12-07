import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from 'src/app/models/Client-model';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-client-update',
  templateUrl: './client-update.component.html',
  styleUrls: ['./client-update.component.css']
})
export class ClientUpdateComponent {

  client_id : any;
   
   
  client : Client = {
    name : '',
    cpf : '',
    phoneNumber : ''
  }

  name = new FormControl('', Validators.minLength(5));
  cpf = new FormControl('', Validators.minLength(11));
  phoneNumber = new FormControl('', Validators.minLength(11));

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

  update() : void {
    this.service.update(this.client)
      .then(() => {
      this.navigateToClients();
      this.service.message("Cliente atualizado com sucesso")
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


