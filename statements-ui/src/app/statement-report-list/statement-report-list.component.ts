import { Component, OnInit } from '@angular/core';
import { StatementReportApiService } from '../statement-report-api-service/statement-report-api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-statement-report-list',
  templateUrl: './statement-report-list.component.html',
  styleUrls: ['./statement-report-list.component.scss']
})
export class StatementReportListComponent implements OnInit {
  reports: any;

  constructor(
    private statementReportApiService: StatementReportApiService,
    private router: Router) { }

  ngOnInit(): void {
    this.statementReportApiService.getAll().subscribe(response => {
      this.reports = response;
    })
  }

  navigateTo(reportId: String) {
    this.router.navigate([`reports/${reportId}`]);
  }

}
