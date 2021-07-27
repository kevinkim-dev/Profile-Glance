<template>
  <div id="app">
    <div v-if="isLogin">
      <Header />
      <router-view />
      <Footer />
    </div>
    <div v-else>
      <Home />
    </div>
  </div>
</template>
<script>
  import Header from '@/components/Header.vue';
  import Footer from '@/components/Footer.vue';
  import Home from '@/views/Home.vue';

  export default {
    mounted() {;
      $.initialize();
    },
    components: {
      Header,
      Footer,
      Home
    },
    computed: {
      isLogin: function() {
        return true
      }
    }
  }
</script>