import Vue from 'vue'
import App from './App.vue'
export const evenBus = new Vue()


import Router from 'vue-router'
import {router} from '@/router/router'

Vue.use(Router)



//Sau khi chạy npm để import bootstarp thì cần import những cái này để sử dụng
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

//Sử dụng fontawsome
import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'


import store from './store/store' 

Vue.config.productionTip = false


new Vue({
  render: h => h(App),
  router,
  store
}).$mount('#app')
