class ValidateTeam{
    checkTeam(team){
        if(team.teamId == null || team.teamName == null){
            alert("Không được để trống mã đơn vị và tên đơn vị ");
            return false;
        }
        team.teamId = team.teamId.trim();
        team.teamName = team.teamName.trim();

        if(team.teamId =="" || team.teamId ==""){
            alert("Không được để trống mã đơn vị và tên đơn vị ");
            return false;
        }
        if(!(team.teamId.length>=2 && team.teamId.length<=50)){
            alert("Mã đơn vị >=2 kí tự và <=50 kí tự ");
            return false;
        }
        if(!(team.teamName.length>=3 && team.teamName.length<=100)){
            alert("Tên đơn vị >=3 kí tự và <=100 kí tự ");
            return false;
        }
        if (team.teamDateCreated != "" && team.teamUpdateDay != "") {
            if (team.teamUpdateDay < team.teamDateCreated) {
                alert("Ngày cập nhật phải <= ngày tạo");
                return false;
            }
        }
        return true;

    }
}
export default new ValidateTeam()