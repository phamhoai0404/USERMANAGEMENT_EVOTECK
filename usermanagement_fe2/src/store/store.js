import Vue from 'vue';
import Vuex from 'vuex';

import { auth } from '@/store/modules/auth.store.js';
import { user } from '@/store/modules/user.store.js';
import { team } from '@/store/modules/team.store.js';


Vue.use(Vuex);

export default new Vuex.Store({
    modules:{
        auth,
        user,
        team
    }
});