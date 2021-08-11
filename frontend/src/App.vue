<template>
  <v-app>
    <div v-if="isLogin">
      <Header />
      <router-view :key="$route.fullPath"/>
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
  methods: {
    refreshAll() {
      this.$router.go()
    }
  },
};
</script>
