import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/index'

import moment from 'moment';
import vuetify from './plugins/vuetify'
import VueMoment from 'vue-moment'

Vue.config.productionTip = false

Vue.use(VueMoment)

Vue.filter('dateFormat', (value) => {
  return moment(value, 'YYYYMMDD').format('YYYY년 MM월 DD일');
});

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
