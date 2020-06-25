import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StatementFileUploadComponent } from './statement-file-upload/statement-file-upload.component';
import { AppComponent } from './app.component';


const routes: Routes = [ {
  path: 'upload',
  component: StatementFileUploadComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
