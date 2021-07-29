import Vue from 'vue'
import Vuex from 'vuex'

import banner from './banner/index';
import product from './product/index';
import cart from './cart/index';
import blog from './blog/index';
import insta from './insta/index';
import mypage from './mypage/index';
import Axios from 'axios';
import Http from '../http.js'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    banner,
    product,
    cart,
    blog,
    insta,
    mypage,
  },
  state: {
    token: '',
    // 0: 관리자, 1: 일반유저, 2: 기업유저
    userType: 2,
    userId: 1
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.token = token
    }
  },
  actions: {
    requestLogin({ commit }, body) {
      console.log('login')
      Http.post('/user/login', body)
      .then(res => {
        console.log('then')
        console.log(res.data)
        commit('SET_TOKEN', res.data)
        localStorage.setItem('user_token', res.data)
      })
      .catch(err => {
        console.log('catch')
        console.log(err)
      })
    }
  }
})
