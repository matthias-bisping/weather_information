export class CurrentWeather {
  messageId : string;
  messageTimestamp : string;
  id : number;
  name : string;
  main : string;
  description : string;
  iconUrl : string;
  temperature : number;
  pressure : number;
  humidity : number;
  windSpeed : number;
  windDegree : number;

  constructor(obj?: any) {
    Object.assign(this, obj);
  }
}
