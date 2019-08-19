import {Component, OnInit} from '@angular/core';
import {RestApiService} from "../shared/rest-api.service";
import {ServerStatus} from "../shared/server-status";

@Component({
  selector: 'app-server-status',
  templateUrl: './server-status.component.html',
  styleUrls: ['./server-status.component.sass']
})
export class ServerStatusComponent implements OnInit {
  serverStatus : ServerStatus;

  constructor(private restApiService : RestApiService) {
    this.serverStatus = new ServerStatus();
    this.serverStatus.status = "";
    this.serverStatus.session = "";
  }

  ngOnInit() {
    this.restApiService.getServerStatus().subscribe((result : ServerStatus) => {
        this.serverStatus = result;
        console.log(result)
    }
    );
  }

}
