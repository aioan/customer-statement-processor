import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { StatementFileUploadComponent } from './statement-file-upload/statement-file-upload.component';
import { MatCardModule } from '@angular/material/card';
import { NavbarComponent } from './navbar/navbar.component';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { StatementReportDetailsComponent } from './statement-report-details/statement-report-details.component';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { StatementReportListComponent } from './statement-report-list/statement-report-list.component';
@NgModule({
  declarations: [
    AppComponent,
    StatementFileUploadComponent,
    NavbarComponent,
    StatementReportDetailsComponent,
    StatementReportListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    MatToolbarModule,
    MatMenuModule,
    FlexLayoutModule,
    HttpClientModule,
    FormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatProgressBarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
