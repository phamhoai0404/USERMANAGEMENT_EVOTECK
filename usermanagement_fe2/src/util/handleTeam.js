class HandleTeam {

    remove(listTeam,team ) {
        if(team == null) return listTeam;
        for (let i = 0; i < listTeam.length; i++) {
            if (team.id == listTeam[i].id) {
                listTeam.splice(i, 1)
                break;
            }
        }
        return listTeam;
    }

}
export default new HandleTeam()