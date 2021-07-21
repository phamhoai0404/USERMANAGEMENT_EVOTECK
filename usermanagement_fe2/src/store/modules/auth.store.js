import AuthService from "@/service/auth.service";

const account = JSON.parse(localStorage.getItem('account'));


//nếu account true thì trả về đối tượng status: - loggindIn: true,
//          -account
//còn false  thì status: - logginIn: false,
//       - account: null
const initState = account ? { status: { loggedIn: true }, account } : { status: { loggedIn: false }, account: null };



export const auth = {
    namespaced: true,
    state: initState,
    getters: {},

    actions: {
        login({ commit }, account) {

            return AuthService.login(account).then(
                account => {
                   // console.log("Đăng nhập tài khoản đúng");
                    commit('loginSuccess', account);
                    return Promise.resolve(account);
                },
                error => {
                    commit('loginFailure');
                   // console.log("Đăng nhập tài khoản sai");
                    return Promise.reject(error);
                }
            );
        },
        logout({ commit }) {
            AuthService.logout();
            commit('logout');
        },
        signup({ commit }, account) {
            return AuthService.signup(account).then(
                response => {
                    commit('registerSuccess');
                    return Promise.resolve(response.data);
                },
                error => {
                    commit('registerFailure');
                    return Promise.reject(error.response.data.message);
                }
            );
        }
       
    },
    mutations: {
        loginSuccess(state, account) {
            state.status.loggedIn = true;
            state.account = account;
        },
        loginFailure(state) {
            state.status.loggedIn = false;
            state.account = null;
        },
        logout(state) {
            state.status.loggedIn = false;
            state.account = null;
        },
        registerSuccess(state) {
            state.status.loggedIn = false;
        },
        registerFailure(state) {
            state.status.loggedIn = false;
        }
    },
}