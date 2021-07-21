export default function authHeader(){
    let account = JSON.parse(localStorage.getItem('account'))
    
    if(account && account.token != null ){
        return {Authorization: 'Bearer ' + account.token};
    }
    else{
        return {};
    }
}