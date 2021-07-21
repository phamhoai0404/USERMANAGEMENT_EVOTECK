import axios from 'axios';
import {url} from '@/key/constants';
import authHeader from '@/service/auth-header';

class UserService{
    actionUser( action, needUrl,inBody){
        return axios({
            method: action,
            url: needUrl,
            data: inBody ,
            headers: authHeader()
        })

    }

    getAllUser() {
        return this.actionUser( 'get' , url.API_USER +'/', null);
    }
    getAllTeam(){
        return this.actionUser('get', url.API_TEAM +'/', null);
    }
    getSearchUser(user){
        var body = {
            searchBasic: user.searchBasic,
            fromDOB: user.fromDOB,
            toDOB: user.toDOB, 
            teamName: user.teamName
        }
        return this.actionUser( 'post' , url.API_USER + '/find' , body);
    }
}
export default new UserService();
