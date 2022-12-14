import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Technician } from 'src/app/models/Technician-model';
import { TechnicianService } from 'src/app/services/technician.service';

@Component({
  selector: 'app-technician-create',
  templateUrl: './technician-create.component.html',
  styleUrls: ['./technician-create.component.css']
})
export class TechnicianCreateComponent {

  technician : Technician = {
    name : '',
    cpf : '',
    phoneNumber : ''
  }

  name = new FormControl('', Validators.minLength(5));
  cpf = new FormControl('', Validators.minLength(11));
  phoneNumber = new FormControl('', Validators.minLength(11));

  constructor(private router: Router, private service : TechnicianService){

  }


  navigateToTechnicians() : void {
    this.router.navigate(['/technician']);
  }

  create() : void {
    this.service.create(this.technician)
      .then(() => {
      this.navigateToTechnicians();
      this.service.message("Técnico criado com sucesso")
      
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
