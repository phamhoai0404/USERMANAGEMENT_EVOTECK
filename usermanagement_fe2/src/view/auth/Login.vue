<template>
    <div class="form-signin row">
        <div class="col-md-4"></div>
        <form class="col-md-4" @submit.prevent="handleLogin">
                <h1 class="h3 mb-3 fw-normal text-center">Đăng nhập</h1>

            <div class="form-floating mb-3">
                <label for="floatingInput">Email address:</label>
                <input type="text" class="form-control" placeholder="name@example.com" v-model="account.email" />

            </div>
            <div class="form-floating mb-2">
                <label for="floatingPassword">Password: </label>
                <input type="password" class="form-control" placeholder="Password" v-model="account.password" />

            </div>
            <div class="mb-2 text-right">
                <router-link to="signup" ref="a">
                     Sign up?
                </router-link>  
            </div>
            <button class="w-100 btn btn-lg btn-primary " type="submit">Sign in</button>
        </form>
    </div>

</template>
<script>
    import Account from "@/model/account.js"
    import Validate from "@/util/validateAccount.js"
    import Authorization from "@/util/authorization.js"
    export default {
        name: "Login",
        data() {
            return {
                account: new Account('', ''),
            }
        },
        mounted() {
           this.$store.dispatch('auth/logout');
        },
        methods: {
            handleLogin() {
                //Nếu thực sự thì cho dòng này vào nhá 
                if(Validate.checkLoginAccount(this.account)){
                   // console.log("this.$store.state.auth.account ngoài là: " + this.$store.state.auth.account);
                    if (this.$store.state.auth.account != null) {
                        this.$store.dispatch('auth/logout').then(
                            () => {
                                this.signin();
                            }
                        );
                    } else {
                        this.signin();
                    }
                }
                    
            },
            signin() {
                this.$store.dispatch('auth/login', this.account).then(
                    () => {
                       // console.log("trạng thái login" + this.$store.state.auth.status.loggedIn);
                        if (this.$store.state.auth.status.loggedIn) {
                            if (Authorization.isAdmin(this.$store.state.auth.account))
                                this.$router.push('/admin');
                            else {
                                this.$router.push('/user');
                            }
                        }
                        else {
                            alert("Mật khẩu hoặc email không đúng ");
                        }
                    },
                    () => {
                        console.log(this.$store.state.auth.status.loggedIn);
                        alert("Mật khẩu hoặc email không đúng ");
                    }
                )
            },

        }
    }

</script>
<style scoped>



</style>