import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Technician } from 'src/app/models/Technician-model';
@Component({
  selector: 'app-technician-read',
  templateUrl: './technician-read.component.html',
  styleUrls: ['./technician-read.component.css']
})
export class TechnicianReadComponent implements AfterViewInit {

  technicians: Technician[] = []


  displayedColumns: string[] = ['id', 'name', 'cpf', 'phoneNumber'];
  dataSource = new MatTableDataSource<Technician>(this.technicians);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
}
