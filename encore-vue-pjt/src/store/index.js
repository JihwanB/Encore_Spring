import {createStore} from 'vuex'

export default createStore({
    state: {
        showToast: false,
        toastMessage: '',
        toastAlertType: '',
        timeOut: null
    },
    mutations: {
        MESSAGE(state, payload) {
            state.toastMessage = payload;
        },
        TYPE(state, payload) {
            state.toastAlertType = payload;
        },
        STATUS(state, payload) {
            state.showToast = payload;
        },
        TIMEOUT(state, payload) {
            state.timeOut = payload;
        }
    },
    actions: {
        triggerToast({commit}, message, type = 'success') {
            console.log(">>> composables >>>> toast.js triggerToast");
            // 아래코드 부분이 vuex 관리되는 상태값이여야 된다.
            commit('MESSAGE', message);
            commit('TYPE', type);
            commit('STATUS', true);

            const timeOut = setTimeout(() => {
                console.log(">>>> setTimeout <<<<");
                commit('MESSAGE', '');
                commit('TYPE', '');
                commit('STATUS', false);
            }, 5000);

            commit('TIMEOUT', timeOut);
        }
    }
});
