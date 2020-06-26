import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StatementFileUploadComponent } from './statement-file-upload/statement-file-upload.component';
import { AppComponent } from './app.component';
import { StatementRequestDetailsComponent } from './statement-request-details/statement-request-details.component';
import { StatementRequestListComponent } from './statement-request-list/statement-request-list.component';


const routes: Routes = [ {
  path: 'upload',
  component: StatementFileUploadComponent
},
{
  path: 'reports/:requestId',
  component: StatementRequestDetailsComponent
},
{
  path: 'reports',
  component: StatementRequestListComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
