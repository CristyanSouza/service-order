import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientCreateComponent } from './views/components/client/client-create/client-create.component';
import { ClientDeleteComponent } from './views/components/client/client-delete/client-delete.component';
import { ClientReadComponent } from './views/components/client/client-read/client-read.component';
import { ClientUpdateComponent } from './views/components/client/client-update/client-update.component';
import { HomeComponent } from './views/components/home/home.component';
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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
