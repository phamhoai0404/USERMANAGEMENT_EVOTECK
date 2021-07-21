

class ValidateAccount{
    
    ckeckSignupAccount(account, confirmPassword){
        if(account.password ==null || account.email == null || confirmPassword == null){
            alert("Không được để trống 'password', 'email' và 'confirm password' " );
            return false;
        }
        if(account.password.trim() ==""){
            alert("Không được để trống 'password'" );
            return false;
        }
        if( !this.validateEmail(account.email)){
            alert("Tài khoản email không hợp lệ !");
            return false;
        }
        if( !this.validatePass(account.password)){
            alert("Mật khẩu không đủ mạnh phải có chữ cái hoa, chữ cái thường, số ,\n kí tự đặc biệt, không có khoảng trống và ít nhất là 8 kí tự !");
            return false;
        } 
        if(account.password != confirmPassword){
            alert("Mật khẩu và xác nhận mật khẩu không khớp ");
            return false;
        }

       
        return true;
        
    }
    checkLoginAccount(account){
        if(account.email == null || account.password == null){
            alert("Không được để trống 'password', 'email' " ); 
            return false;
        }
        if(account.email.trim() =="" || account.password.trim() ==""){
            alert("Không được để trống 'email' " );
            return false;
        }
        if(!this.validateEmail(account.email)){
            alert("Tài khoản email không hợp lệ ");
            return false;
        }
        return true;   
    }



    validateEmail(email) {
        const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(String(email).toLocaleLowerCase());
    }
    validatePass(password){
        const re = /^(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])(?=.*[a-z]).{8,}$/;
        var space = password.indexOf(' ') == -1 ? true : false;
        return re.test(password)&& space;
    }

    
}
export default new ValidateAccount()