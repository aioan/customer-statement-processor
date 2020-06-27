import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StatementFileUploadComponent } from './statement-file-upload/statement-file-upload.component';
import { AppComponent } from './app.component';
import { StatementReportDetailsComponent } from './statement-report-details/statement-report-details.component';
import { StatementReportListComponent } from './statement-report-list/statement-report-list.component';


const routes: Routes = [ {
  path: 'upload',
  component: StatementFileUploadComponent
},
{
  path: 'reports/:requestId',
  component: StatementReportDetailsComponent
},
{
  path: 'reports',
  component: StatementReportListComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
