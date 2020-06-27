import { Component, OnInit, OnDestroy } from '@angular/core';
import { StatementReportApiService } from '../statement-report-api-service/statement-report-api.service';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-statement-report-details',
  templateUrl: './statement-report-details.component.html',
  styleUrls: ['./statement-report-details.component.scss']
})
export class StatementReportDetailsComponent implements OnInit, OnDestroy {
  requestId: any;
  errors: any;
  loading = false;
  private sub: Subscription;
  duration: number;
  errorMessage: string;
  processedCount: number;
  throughput: String;

  constructor(
    private statementErrorApiService: StatementReportApiService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.loading = true;
    this.sub = this.route.params.subscribe(params => {
      this.requestId = params.requestId;
      this.statementErrorApiService.getById(this.requestId).subscribe(response => {
        this.errors = response.statementErrors;
        this.processedCount = response.processedEntriesCount;
        if(response.finishDate) {
          this.duration = new Date(response.finishDate).getTime() - new Date(response.startDate).getTime();
          this.throughput = (this.processedCount / (this.duration / 1000)).toFixed(0); 
        }
      },
      e => {
        if(e.status == 404) {
          this.errorMessage = "Your request was not found. Please double check your request id.";
        } else {
          this.errorMessage = "There was an unexpected error while retrieving your request. Please try again later.";
        }
      }).add(() =>  this.loading = false);
    });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

}
