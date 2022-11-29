import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './views/components/home/home.component';
import { TechnicianReadComponent } from './views/components/technician/technician-read/technician-read.component';

const routes: Routes = [
  {
    'path': '',
    'component': HomeComponent
  },

  {
    'path': 'technician',
    'component': TechnicianReadComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
