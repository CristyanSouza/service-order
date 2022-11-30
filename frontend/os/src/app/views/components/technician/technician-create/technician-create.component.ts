import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Technician } from 'src/app/models/Technician-model';
import { TechnicianService } from 'src/app/services/technician.service';

@Component({
  selector: 'app-technician-create',
  templateUrl: './technician-create.component.html',
  styleUrls: ['./technician-create.component.css']
})
export class TechnicianCreateComponent {

  testTechnician : Technician = {
    name : 'Teste Criação Técnico',
    cpf : '615.035.210-28',
    phoneNumber : '48 9 98307254'
  }


  constructor(private router: Router, private service : TechnicianService){

  }


  navigateToTechnicians() : void {
    this.router.navigate(['/technician']);
  }

  create() : void {
    this.service.create(this.testTechnician)
      .then(() => {
      this.navigateToTechnicians();
      this.service.message("Técnico criado com sucesso")
      
    })
    .catch(error => {
      console.log(error.response.data.error)
    });

  }
  
}
