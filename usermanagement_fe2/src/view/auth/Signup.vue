<template>
    <div class="form-signin row">
        <div class="col-md-4"></div>
        <form class="col-md-4" @submit.prevent="handleSignup">
            <h1 class="h3 mb-3 fw-normal text-center">Tạo tài khoản</h1>

            <div class="form-floating mb-3">
                <label for="floatingInput">Username: </label>
                <input 
                    type="text" class="form-control" 
                    placeholder="Phạm Thị Thu Trúc"
                    v-model="account.username" />
            </div>
            <span></span>

            <div class="form-floating mb-3">
                <label for="floatingInput">Email address <span>*</span>: </label>
                <input 
                    type="text" class="form-control" 
                    placeholder="name@example.com"
                    v-model="account.email"
                    />
                <span></span>
            </div>

            <div class="form-floating mb-3">
                <label for="floatingPassword">Password <span>*</span>: </label>
                <input type="password" class="form-control" placeholder=" New Password"
                    v-model="account.password"
                />
                <span></span>
            </div>

            <div class="form-floating mb-4">
                <label for="floatingPassword">Confirm Password <span>*</span>: </label>
                <input type="password" class="form-control" placeholder=" Confirm Password"
                    v-model="confirmPassword"
                />

                <span></span>

            </div>
            <div class="checkbox mb-3">
                <label>
                    <input type="checkbox" value="remember-me"> Tôi đồng ý với chính sách bảo mật
                </label>
            </div>
            <button class="w-100 btn btn-lg btn-primary " type="submit">Đăng kí ngay</button>
        </form>
    </div>

</template>
<script>
    import Account from "@/model/account.js"
    import Validate from "@/util/validateAccount.js"
    export default {
        name: 'Signup',
        data() {
            return {
                account: new Account(),
                confirmPassword: null,
            }
        },
        methods: {
            handleSignup() {
               if( Validate.ckeckSignupAccount(this.account, this.confirmPassword)){
                    this.$store.dispatch('auth/signup',this.account).then(
                        ()=>{
                            alert("Bạn đã đăng kí thành công! \n\n Về login để đăng nhập");
                            this.$router.replace('/');
                        },
                        ()=>{
                            alert("Tài khoản email đã được đăng kí !!");
                        }
                    )
               }            
            }
        },
    }
</script>
<style scoped>
    label span {
        color: red;
    }
</style>