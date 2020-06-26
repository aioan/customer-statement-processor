import { Component, OnInit, OnDestroy } from '@angular/core';
import { StatementErrorApiService } from '../statement-error-api-service/statement-error-api.service'
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-statement-error-details',
  templateUrl: './statement-error-details.component.html',
  styleUrls: ['./statement-error-details.component.scss']
})
export class StatementErrorDetailsComponent implements OnInit, OnDestroy {
  requestId: any;
  errors: any;
  loading = false;
  private sub: any;

  constructor(
    private statementErrorApiService: StatementErrorApiService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.loading = true;
    this.sub = this.route.params.subscribe(params => {
      this.requestId = params.requestId;
      this.statementErrorApiService.getErrors(this.requestId).subscribe(response => {
        this.errors = response;
        this.loading = false;
      });
    });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

}
