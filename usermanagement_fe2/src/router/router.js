import Vue from 'vue'
import Router from 'vue-router'
import Authorization from '@/util/authorization.js'

import Login from '@/view/auth/Login'
import Signup from '@/view/auth/Signup'
import MainUser from '@/view/mainpage/MainUser'
import MainAdmin from '@/view/mainpage/MainAdmin'





Vue.use(Router)
export const router = new Router({
     mode: 'history',
     routes:
          [
               {
                    path: '/', name: 'Login', component: Login,
               },
               { path: '/signup', name: 'Signup', component: Signup },
               { path: '/user', name: 'MainUser', component: MainUser },
               { path: '/admin', name: 'MainAdmin', component: MainAdmin },
               
          ]
})


router.beforeEach((to, from, next) => {

     const publicPage = ['Login','Signup'];
     const adminPage = ['MainAdmin'];
     const userPage = ['MainUser'];
          let loggedIn = JSON.parse(localStorage.getItem('account'));
     
     //console.log(loggedIn);
     
     

     if(publicPage.includes(to.name)){
          next();
     }
     if(loggedIn !=null){
          
          let account = Authorization.isAdmin(loggedIn);
          //console.log("tài khoản là: " + account);
          if(account && adminPage.includes(to.name)){
               next();
          }
          if(!account && userPage.includes(to.name)){
               next();
          }
     }
     
});
