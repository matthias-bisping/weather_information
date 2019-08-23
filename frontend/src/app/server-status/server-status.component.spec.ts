import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ServerStatusComponent} from './server-status.component';
import {RestApiService} from "../shared/rest-api.service";
import {Observable, of} from "rxjs";
import {ServerStatus} from "../shared/server-status";

class MockRestApiService {

  getServerStatus() : Observable<ServerStatus> {
    let result : ServerStatus = {
      session:  "Test",
      status: "Test"
    };
    return of(result)
  }
}

describe('ServerStatusComponent', () => {
  let component: ServerStatusComponent;
  let fixture: ComponentFixture<ServerStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({

      declarations: [ ServerStatusComponent ],
      providers: [
        {
          provide: RestApiService, useClass: MockRestApiService
        }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServerStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
