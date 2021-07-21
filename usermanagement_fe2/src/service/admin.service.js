import axios from 'axios';
import { url } from '@/key/constants';
import authHeader from '@/service/auth-header';

const USER = url.API_USER;
const   TEAM = url.API_TEAM;
class AdminService {
    actionAdmin(needUrl, action, endpoint, inBody) {
        return axios({
            method: action,
            url: needUrl + endpoint,
            data: inBody,
            headers: authHeader()
        })

    }
    getAllUser() {
        return this.actionAdmin(USER, 'get', '/', null);
    }
    editUser(user) {
        var body ={
            address: user.address,
            dateCreated: user.dateCreated,
            dateOfBirth: user.dateOfBirth,
            fullName: user.fullName,
            maker: user.maker,
            updateDay: user.updateDay,
            updater: user.updater,
            userDescribe: user.userDescribe, 
        }
        if(user.team != null){
            body.team = user.team;
        }
        return this.actionAdmin(USER, 'put', '/update/' + user.id, body );
    }
    addUser(user){
        var body ={
            address: user.address,
            dateCreated: user.dateCreated,
            dateOfBirth: user.dateOfBirth,
            fullName: user.fullName,
            maker: user.maker,
            updateDay: user.updateDay,
            updater: user.updater,
            userDescribe: user.userDescribe,
        }
        if(user.team != null){
            body.team = user.team;
        }
        return this.actionAdmin(USER, 'post', '/create', body );
    }
    deleteUser(user){
        return this.actionAdmin(USER, 'delete', '/delete/'+user.id,null);
    }
    getAllTeam(){
        return this.actionAdmin(TEAM, 'get', '/', null);
    }
    deleteTeam(team){
        return this.actionAdmin(TEAM, 'delete', '/delete/'+team.id,null);
    }
    editTeam(team){
        var body ={
            teamDateCreated: team.teamDateCreated,
            teamDescribe: team.teamDescribe,
            teamId: team.teamId,
            teamMaker: team.teamMaker,
            teamName: team.teamName,
            teamUpdateDay: team.teamUpdateDay,
            teamUpdater: team.teamUpdater
        }
        if(team.teamGroup != null){
            body.teamGroup = team.teamGroup;
        }
        return this.actionAdmin(TEAM, 'put', '/update/' + team.id, body );
    }
    addTeam(team){
        var body ={
            teamDateCreated: team.teamDateCreated,
            teamDescribe: team.teamDescribe,
            teamId: team.teamId,
            teamMaker: team.teamMaker,
            teamName: team.teamName,
            teamUpdateDay: team.teamUpdateDay,
            teamUpdater: team.teamUpdater
        }
        if(team.teamGroup != null){
            body.teamGroup = team.teamGroup;
        }
        return this.actionAdmin(TEAM, 'post', '/create', body );
    }
}
export default new AdminService();
