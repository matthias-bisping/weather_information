export class ServerStatus {
  status : string;
  session : string;

  constructor(obj?: any) {
    Object.assign(this, obj);
  }
}
