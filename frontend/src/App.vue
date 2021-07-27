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


export default {
  name: 'App',

  components: {
    Header,
    Footer,
    Home
  },

  data: () => ({
    //
  }),
  computed: {
    isLogin: function() {
      return true
    }
  }

};
</script>
