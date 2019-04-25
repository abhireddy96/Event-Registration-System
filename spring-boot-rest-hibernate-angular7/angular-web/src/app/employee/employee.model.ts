export class Employee {

    public id: string;
    public name: string;
    public emailID: string;
    public joinDate: string;

    constructor(id?: string, name?: string, emailID?: string, joinDate?: string) {
      this.id = id;
      this.name = name;
      this.emailID = emailID;
      this.joinDate = joinDate;
    }
}
