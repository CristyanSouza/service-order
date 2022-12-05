import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientReadComponent } from './views/components/client/client-read/client-read.component';
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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
