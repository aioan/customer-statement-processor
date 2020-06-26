import { Component, OnInit } from '@angular/core';
import { StatementApiService } from '../statement-api-service/statement-api.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-statement-file-upload',
  templateUrl: './statement-file-upload.component.html',
  styleUrls: ['./statement-file-upload.component.scss']
})
export class StatementFileUploadComponent implements OnInit {
  file: File;
  fileName: String;
  loading = false;
  errorMessage: string;
  invalidFile = false;

  constructor(
    private statementApi: StatementApiService,
    private router: Router) { }

  ngOnInit(): void {
  }

  upload(): void {
    this.errorMessage = null;
    this.loading = true;
    this.statementApi.uploadFile(this.file).subscribe(result => {
      this.router.navigate([`reports/${result.requestId}`]);
    },
    e => {
      switch(e.error.code) {
        case 'UNSUPPORTED_FILE_TYPE': 
          this.errorMessage = "The file you uploaded is not supported, please upload a .csv or .xml file.";
          this.invalidFile = true;
          break;
        case 'INVALID_FILE_STRUCTURE':
          this.errorMessage = "The file you uploaded is corrupted. Please check the file structure and try again.";
          this.invalidFile = true;
          break;
        default:
          this.errorMessage = "An unexpected error ocurred while processing your request. Please try again later.";
      }
    }).add(() =>  this.loading = false);;
  }

  fileSelected(files: FileList) {
    this.file = files.item(0);
    this.fileName = this.file.name;

    if(this.file.size / 1024 / 1024 > 15) {
      this.errorMessage = "Maximum file size is 15 mb, please select another file.";
      this.invalidFile = true;
    } else if(!this.fileName.endsWith('xml') && !this.fileName.endsWith('csv')) {
      this.errorMessage = "The file you selected is not supported, please upload a .csv or .xml file.";
      this.invalidFile = true;
    } else {
      this.invalidFile = false;
      this.errorMessage = null;
    }
  }
  
}
