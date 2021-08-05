<template>
  <v-app>
    <div v-if="isLogin">
      <Header />
      <router-view />
      <Footer />
    </div>
    <div v-else>
      <Home />
    </div>


    <!-- <v-main>
      <HelloWorld/>
    </v-main> -->
  </v-app>
</template>

<script>
import Header from '@/components/Header.vue';
import Footer from '@/components/Footer.vue';
import Home from '@/views/Home.vue';
import Http from '@/http.js'

export default {
  name: 'App',

  components: {
    Header,
    Footer,
    Home
  },

  data: () => ({
  }),
  computed: {
    isLogin: function() {
      return localStorage.getItem('token')
    }
  },
  mounted () {
    if (localStorage.getItem('token')) {
      this.$store.dispatch('setToken', localStorage.getItem('token'))
      if (!localStorage.getItem('isCompany')) {
        Http.get('/user/myinfo/' + localStorage.getItem('id'))
        .then(res => {
          console.log('get user data')
          this.$store.dispatch('updateUserInfo', res.data)
        })
        .catch(err => {
          console.log(err)
        })
      } else {
        console.log('get company data')
      }
    }
  }
};
</script>
