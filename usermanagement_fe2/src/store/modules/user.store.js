// import UserService from "@/service/user.service";

export const user = {
    namespaced: true,
    state: {
        dataUser : null,
        transferDataRepair:null,
        transfreDataDelete: null,
        buttonShowRepair: false,
        buttonShowAdd: false,
        buttonShowDelete: false
    },
    getters: {

    },

    actions: {
        //Toàn bất đồng bộ thôi nên sử lý nó ở component rồi 
        // getDataAllUser({ commit }) {
        //     UserService.getAllUser()
        //         .then(
        //             response => {
        //                 commit('getDataAllUser', response.data);
        //                 console.log(response.data);
        //                 return Promise.resolve(response.data);
        //             },
        //             error => {
        //                 commit('setNullData');
        //                 console.log( error.response);
        //                 return Promise.reject(error.response);
        //             });
        // }

   // },
    },
    mutations: {
        getDataAllUser(state, dulieu) {
            state.dataAllUser = dulieu;
        },
        setNullData(state) {
            state.dataAllUser = null;
        }
    },

}