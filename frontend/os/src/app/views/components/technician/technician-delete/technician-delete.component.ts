import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Technician } from 'src/app/models/Technician-model';
import { TechnicianService } from 'src/app/services/technician.service';

@Component({
  selector: 'app-technician-delete',
  templateUrl: './technician-delete.component.html',
  styleUrls: ['./technician-delete.component.css']
})
export class TechnicianDeleteComponent {

  tech_id : any;
  
  
 technician : Technician = {
   id: '',
   name : '',
   cpf : '',
   phoneNumber : ''
 }

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

 delete() : void {
  this.service.delete(this.technician)
    .then(response => {
      response.status
      this.service.message("Técnico deletado com sucesso");
        })
      .catch(err => {
        if(err.response.data.error == "O funcionário possui ordens de serviços vinculadas"){
          this.service.message("O funcionário possui ordens de serviços vinculadas");
        }
      })
      .finally(() => {
        this.navigateToTechnicians();
      }
      );
 }
}
