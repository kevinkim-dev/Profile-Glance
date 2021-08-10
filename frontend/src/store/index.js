import Vue from 'vue';
import Vuex from 'vuex';

import banner from './banner/index';
import product from './product/index';
import cart from './cart/index';
import blog from './blog/index';
import insta from './insta/index';
import mypage from './mypage/index';
import wanted from './wanted/index';
import lookatme from './lookatme/index';
import createPersistedState from 'vuex-persistedstate';
import Http from '../http.js';
import router from '../router';

Vue.use(Vuex);
// 개발모드면 true 배포모드면 false
const DEVELOPMODE = true;
// const DEVELOPMODE = false
export default new Vuex.Store({
  modules: {
    banner,
    product,
    cart,
    blog,
    insta,
    mypage,
    wanted,
    lookatme,
  },
  plugins: [createPersistedState()],
  getters: {
    fileURL: function() {
      return DEVELOPMODE ? 'http://localhost:8080/' : 'https://profileglance.site/';
    },
    DEVELOPMODE: function() {
      return DEVELOPMODE;
    },
  },
  state: {
    data: {},
  },
  mutations: {
    REQUEST_LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('login_type');
      localStorage.removeItem('user_email');
      localStorage.removeItem('id');
      localStorage.removeItem('vuex');
      router.push('/');
      location.reload();
    },
  },
  actions: {
    requestDeleteUser({ commit }, userEmail) {
      console.log(userEmail);
      Http.delete('/user/delete/' + userEmail)
        .then((res) => {
          console.log('then');
          commit('REQUEST_LOGOUT');
        })
        .catch((err) => {
          console.log('catch');
          console.log(err);
        });
    },
    requestLogout({ commit }) {
      commit('REQUEST_LOGOUT');
    },
  },
});
