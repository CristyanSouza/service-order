import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceOrder } from 'src/app/models/ServiceOrder-model';
import { ServiceorderService } from 'src/app/services/serviceorder.service';

@Component({
  selector: 'app-serviceorder-view',
  templateUrl: './serviceorder-view.component.html',
  styleUrls: ['./serviceorder-view.component.css']
})
export class ServiceorderViewComponent {
  
  soModel : ServiceOrder = {
    priority : '',
    comments: '',
    status : '',
    technician : '',
    client : '',
    openingDate: '',
    closingDate: ''

}

  soView: ServiceOrder = {
    priority : '',
    comments: '',
    status : '',
    technician : '',
    client : '',
    openingDate: '',
    closingDate: ''

  };

  constructor(private route : ActivatedRoute, private service : ServiceorderService,
    private router : Router) {

  }


  ngOnInit(): void {
    this.soModel.id = this.route.snapshot.paramMap.get("id");
    this.soView.id = this.route.snapshot.paramMap.get("id");
    this.findById();
  }

  findById(): void {
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

  navigateToServicesOrder(){
    this.router.navigate(['/serviceorder']);
  }
}
