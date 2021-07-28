import Vue from 'vue'
import Vuex from 'vuex'

import banner from './banner/index';
import product from './product/index';
import cart from './cart/index';
import blog from './blog/index';
import insta from './insta/index';

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    banner,
    product,
    cart,
    blog,
    insta
  },
  state: {
    token: '',
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.token = token
    }
  },
  actions: {
    requestLogin({ commit }) {
      commit('SET_TOKEN', 'd')
    }
  }
})
