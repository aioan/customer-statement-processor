import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StatementFileUploadComponent } from './statement-file-upload/statement-file-upload.component';
import { AppComponent } from './app.component';
import { StatementErrorDetailsComponent } from './statement-error-details/statement-error-details.component';


const routes: Routes = [ {
  path: 'upload',
  component: StatementFileUploadComponent
},
{
  path: 'errors/:requestId',
  component: StatementErrorDetailsComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
