import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ServerStatusComponent} from './server-status.component';
import {RestApiService} from "../shared/rest-api.service";
import {MockRestApiService} from "../shared/app.testdata";

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
