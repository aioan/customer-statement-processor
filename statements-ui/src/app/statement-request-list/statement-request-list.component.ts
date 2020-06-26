import { Component, OnInit } from '@angular/core';
import { StatementRequestApiService } from '../statement-request-api-service/statement-request-api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-statement-request-list',
  templateUrl: './statement-request-list.component.html',
  styleUrls: ['./statement-request-list.component.scss']
})
export class StatementRequestListComponent implements OnInit {
  reports: any;

  constructor(
    private statementRequestApiService: StatementRequestApiService,
    private router: Router) { }

  ngOnInit(): void {
    this.statementRequestApiService.getAll().subscribe(response => {
      this.reports = response;
    })
  }

  navigateTo(reportId: String) {
    this.router.navigate([`reports/${reportId}`]);
  }

}
