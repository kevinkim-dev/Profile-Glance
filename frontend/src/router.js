import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import LookAtMe from './views/LookAtMe.vue'
import Wanted from './views/Wanted.vue'
import Features from './views/Features';
import Mypage from './views/Mypage.vue';

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/lookatme',
      name: 'lookatme',
      component: LookAtMe
    },
    {
      path: '/wanted',
      name: 'wanted',
      component: Wanted
    },
    {
      path: '/features',
      name: 'features',
      component: Features
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: Mypage
    }
  ]
})
