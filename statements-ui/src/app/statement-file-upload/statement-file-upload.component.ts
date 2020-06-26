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

  constructor(
    private statementApi: StatementApiService,
    private router: Router) { }

  ngOnInit(): void {
  }

  upload(): void {
    this.loading = true;
    this.statementApi.uploadFile(this.file).subscribe(result => {
      this.loading = false;
      
      this.router.navigate([`errors/${result.requestId}`]);
    });
  }

  fileSelected(files: FileList) {
    this.file = files.item(0);
    this.fileName = this.file.name;
  }
}
