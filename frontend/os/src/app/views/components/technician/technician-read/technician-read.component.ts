import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Technician } from 'src/app/models/Technician-model';
import { TechnicianService } from 'src/app/services/technician.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-technician-read',
  templateUrl: './technician-read.component.html',
  styleUrls: ['./technician-read.component.css']
})
export class TechnicianReadComponent implements AfterViewInit {

  constructor(private service : TechnicianService, private router : Router){
  }

  technicians: Technician[] = [];


  displayedColumns: string[] = ['id', 'name', 'cpf', 'phoneNumber', 'action'];
  dataSource = new MatTableDataSource<Technician>(this.technicians);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngAfterViewInit() {
    this.findAll();
  }

  findAll(): void {
    this.service.findAll().subscribe((response) => {
      this.technicians = response;
      this.dataSource = new MatTableDataSource<Technician>(this.technicians);
      this.dataSource.paginator = this.paginator;
    })
    }

    navigateToCreate() : void {
      this.router.navigate(['technician/create']);
    }
  }

