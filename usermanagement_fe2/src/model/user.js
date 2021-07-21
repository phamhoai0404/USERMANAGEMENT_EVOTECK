export default class User{
    constructor(id, fullName,userDescribe,dateOfBirth,dateCreated, address, maker, updateDay, updater,team){
        this.id =id;
        this.fullName = fullName;
        this.userDescribe = userDescribe;
        this.dateOfBirth = dateOfBirth;
        this.dateCreated =dateCreated;
        this.address=address;
        this.maker=maker;
        this.updateDay=updateDay;
        this.updater = updater;
        this.team = team;
    }
}