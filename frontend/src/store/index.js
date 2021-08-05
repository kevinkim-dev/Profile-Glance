import Vue from 'vue'
import Vuex from 'vuex'

import banner from './banner/index';
import product from './product/index';
import cart from './cart/index';
import blog from './blog/index';
import insta from './insta/index';
import mypage from './mypage/index';
import wanted from './wanted/index';
import lookatme from './lookatme/index';

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
    wanted,
    lookatme,
  },
  state: {
    token: '',
    // 0: 관리자, 1: 일반유저, 2: 기업유저
    userType: 1,
    userId: 1,
    userData: {
      userEmail: '',
      userName: '',
      userNickname: '',
      userBirth: '',
      major1: '',
      major2: '',
      countLike: 0,
      countVideo: 0,
      portfolio1: '',
      portfolio2: '',
      userImg: '',
    },
    companyData: {
      companyId: '',
      companyEmail: '',
      companyName: '',
      companyPhone: '',
      companyImg: '',
    }
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.token = token
    },
    DELETE_TOKEN(state) {
      state.token = ''
    },
    UPDATE_USER_INFO(state, userData) {
      state.userData = userData
      state.userType = 1
    },
    UPDATE_COMPANY_INFO(state, companyData) {
      state.companyData = companyData
      state.userType = 2
    }
  },
  actions: {
    setToken({ commit }, token) {
      commit('SET_TOKEN', token)
    },
    updateUserInfo({ commit }, userData) {
      commit('UPDATE_USER_INFO', userData)
    },
    updateCompanyInfo({ commit }, companyData) {
      commit('UPDATE_COMPANY_INFO', companyData)
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
