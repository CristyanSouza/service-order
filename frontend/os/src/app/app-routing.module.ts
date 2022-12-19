import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientCreateComponent } from './views/components/client/client-create/client-create.component';
import { ClientDeleteComponent } from './views/components/client/client-delete/client-delete.component';
import { ClientReadComponent } from './views/components/client/client-read/client-read.component';
import { ClientUpdateComponent } from './views/components/client/client-update/client-update.component';
import { HomeComponent } from './views/components/home/home.component';
import { ServiceorderCreateComponent } from './views/components/serviceorder/serviceorder-create/serviceorder-create.component';
import { ServiceorderReadComponent } from './views/components/serviceorder/serviceorder-read/serviceorder-read.component';
import { ServiceorderUpdateComponent } from './views/components/serviceorder/serviceorder-update/serviceorder-update.component';
import { ServiceorderViewComponent } from './views/components/serviceorder/serviceorder-view/serviceorder-view.component';
import { TechnicianCreateComponent } from './views/components/technician/technician-create/technician-create.component';
import { TechnicianDeleteComponent } from './views/components/technician/technician-delete/technician-delete.component';
import { TechnicianReadComponent } from './views/components/technician/technician-read/technician-read.component';
import { TechnicianUpdateComponent } from './views/components/technician/technician-update/technician-update.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },

  {
    path: 'technician',
    component: TechnicianReadComponent
  },

  {
    path: 'technician/create',
    component: TechnicianCreateComponent
  },

  {
    path: 'technician/update/:id',
    component: TechnicianUpdateComponent
  },

  {
    path: 'technician/delete/:id',
    component: TechnicianDeleteComponent
  },

  {
    path: 'client',
    component: ClientReadComponent
  },
  {
    path: 'client/create',
    component: ClientCreateComponent
  },
  {
    path: 'client/update/:id',
    component: ClientUpdateComponent
  },
  {
    path: 'client/delete/:id',
    component: ClientDeleteComponent
  },

  {
    path: 'serviceorder',
    component: ServiceorderReadComponent
  },

  {
    path: 'serviceorder/create',
    component: ServiceorderCreateComponent
  },

  {
    path: 'serviceorder/update/:id',
    component: ServiceorderUpdateComponent
  },

  {
    path: 'serviceorder/view/:id',
    component: ServiceorderViewComponent
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
