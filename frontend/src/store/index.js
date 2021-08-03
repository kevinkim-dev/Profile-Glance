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
import VueRouter from 'vue-router';

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
    userType: 1,
    userId: 1,
    userEmail: 'test6@test.com',
    userName: 'test6',
    userNickname: 'test6',
    userBirth: '1995-10-30',
    userPhone: '010-1010-1010',
    major1: '전자',
    major2: '기계',
    companyLike: 0,
    portfolio1: 'git',
    portfolio2: 'git',
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.token = token
    },
    DELETE_TOKEN(state) {
      state.token = ''
    },
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
    },
    requestDeleteUser({ commit }) {
      console.log('delete')
      Http.delete('/user/delete/' + 'test2@test.com')
      .then(res => {
        console.log('then')
        commit('DELETE_TOKEN')
        localStorage.removeItem('user_token')
        location.href = "http://localhost:8080"
      })
      .catch(err => {
        console.log('catch')
        console.log(err)
      })
    },
  }
})
