import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import {MatToolbarModule} from '@angular/material/toolbar'; 
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import {MatListModule} from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
import { HeaderComponent } from './views/components/template/header/header.component';
import { FooterComponent } from './views/components/template/footer/footer.component';
import { NavComponent } from './views/components/template/nav/nav.component';
import { HomeComponent } from './views/components/home/home.component';
import { TechnicianReadComponent } from './views/components/technician/technician-read/technician-read.component';
import {MatTableModule} from '@angular/material/table'; 
import {MatPaginatorModule} from '@angular/material/paginator';
import { TechnicianCreateComponent } from './views/components/technician/technician-create/technician-create.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { TechnicianUpdateComponent } from './views/components/technician/technician-update/technician-update.component';
import { TechnicianDeleteComponent } from './views/components/technician/technician-delete/technician-delete.component';
import { ClientReadComponent } from './views/components/client/client-read/client-read.component';
import { ClientCreateComponent } from './views/components/client/client-create/client-create.component';
import { ClientUpdateComponent } from './views/components/client/client-update/client-update.component';
import { ClientDeleteComponent } from './views/components/client/client-delete/client-delete.component';
import { ServiceorderReadComponent } from './views/components/serviceorder/serviceorder-read/serviceorder-read.component';
import { ServiceorderCreateComponent } from './views/components/serviceorder/serviceorder-create/serviceorder-create.component';
import { ServiceorderUpdateComponent } from './views/components/serviceorder/serviceorder-update/serviceorder-update.component';
import { ServiceorderViewComponent } from './views/components/serviceorder/serviceorder-view/serviceorder-view.component'; 


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    NavComponent,
    HomeComponent,
    TechnicianReadComponent,
    TechnicianCreateComponent,
    TechnicianUpdateComponent,
    TechnicianDeleteComponent,
    ClientReadComponent,
    ClientCreateComponent,
    ClientUpdateComponent,
    ClientDeleteComponent,
    ServiceorderReadComponent,
    ServiceorderCreateComponent,
    ServiceorderUpdateComponent,
    ServiceorderViewComponent
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatSidenavModule,
    MatIconModule,
    MatButtonModule,
    MatListModule,
    MatCardModule,
    MatTableModule,
    MatSelectModule,
    MatInputModule,
    MatDatepickerModule,
    FormsModule,
    ReactiveFormsModule,
    MatPaginatorModule,
    MatSnackBarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
