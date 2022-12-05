import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Technician } from 'src/app/models/Technician-model';
import { TechnicianService } from 'src/app/services/technician.service';

@Component({
  selector: 'app-technician-update',
  templateUrl: './technician-update.component.html',
  styleUrls: ['./technician-update.component.css']
})
export class TechnicianUpdateComponent {

   tech_id : any;
   
   
  technician : Technician = {
    name : '',
    cpf : '',
    phoneNumber : ''
  }

  name = new FormControl('', Validators.minLength(5));
  cpf = new FormControl('', Validators.minLength(11));
  phoneNumber = new FormControl('', Validators.minLength(11));

  constructor(
    private router: Router, 
    private service : TechnicianService,
    private route : ActivatedRoute
    ){

  }

  ngOnInit(): void {
    this.tech_id = this.route.snapshot.paramMap.get('id')

    this.service.findById(this.tech_id)
      .then(response => {
        this.technician = response.data;
      })
  }

  navigateToTechnicians() : void {
    this.router.navigate(['/technician']);
  }

  update() : void {
    this.service.update(this.technician)
      .then(() => {
      this.navigateToTechnicians();
      this.service.message("Técnico atualizado com sucesso")
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
