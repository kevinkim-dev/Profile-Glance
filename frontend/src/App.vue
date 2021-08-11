<template>
  <v-app>
    <div v-if="isLogin">
      <Header v-if="inNotSession" />
      <router-view :key="$route.fullPath" />
      <Footer v-if="inNotSession" />
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
import Http from '@/http.js';

export default {
  name: 'App',

  components: {
    Header,
    Footer,
    Home,
  },

  data: () => ({}),
  computed: {
    isLogin: function() {
      return localStorage.getItem('token');
    },
    inNotSession: function() {
      console.log(localStorage.getItem('isSession') != 'true')
      return localStorage.getItem('isSession') != 'true'
    }
  },
  methods: {
    refreshAll() {
      this.$router.go();
    },
  },
};
</script>
