import axios from 'axios'
import {url} from '@/key/constants.js'

class AuthService{
    login(account){
        return axios.post(url.API_AUTH + '/signin', {
            email: account.email,
            password: account.password
        }) 
        .then(response => {
            //console.log(response.data.token);
            if(response.data.token !=""){
                localStorage.setItem('account', JSON.stringify(response.data));
            }
            return response.data;
        });
    }

    logout(){
        localStorage.removeItem('account');
    }

    signup(account){
        return axios.post(url.API_AUTH + '/signup', {
            username: account.username,
            email:  account.email,
            password: account.password
        })
    }
}
export default new AuthService();