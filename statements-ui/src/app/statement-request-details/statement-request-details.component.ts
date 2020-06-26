import { Component, OnInit, OnDestroy } from '@angular/core';
import { StatementRequestApiService } from '../statement-request-api-service/statement-request-api.service';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-statement-request-details',
  templateUrl: './statement-request-details.component.html',
  styleUrls: ['./statement-request-details.component.scss']
})
export class StatementRequestDetailsComponent implements OnInit, OnDestroy {
  requestId: any;
  errors: any;
  loading = false;
  private sub: Subscription;
  duration: number;
  errorMessage: string;

  constructor(
    private statementErrorApiService: StatementRequestApiService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.loading = true;
    this.sub = this.route.params.subscribe(params => {
      this.requestId = params.requestId;
      this.statementErrorApiService.getById(this.requestId).subscribe(response => {
        this.errors = response.statementErrors;
        if(response.finishedDate) {
          this.duration = new Date(response.finishedDate).getTime() - new Date(response.createdDate).getTime();
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
