import { Component, OnInit } from '@angular/core';
import { StatementApiService } from '../statement-api-service/statement-api.service'

@Component({
  selector: 'app-statement-file-upload',
  templateUrl: './statement-file-upload.component.html',
  styleUrls: ['./statement-file-upload.component.scss']
})
export class StatementFileUploadComponent implements OnInit {
  private file: File;

  constructor(private statementApi: StatementApiService) { }

  ngOnInit(): void {
  }

  upload(): void {
    this.statementApi.uploadFile(this.file).subscribe(result => {
      console.log(JSON.stringify(result));
    });
  }

  fileSelected(files: FileList) {
    this.file = files.item(0);
  }
}
