export default class Team{
    constructor(id, teamName, teamId, teamGroup, teamDescribe,teamDateCreated,teamMaker, teamUpdateDay,teamUpdater ){
        this.id = id;
        this.teamName = teamName;
        this.teamId = teamId;
        this.teamDescribe = teamDescribe;
        this.teamGroup = teamGroup;
        this.teamDateCreated = teamDateCreated;
        this.teamMaker = teamMaker;
        this.teamUpdateDay = teamUpdateDay;
        this.teamUpdater = teamUpdater;
    }
}