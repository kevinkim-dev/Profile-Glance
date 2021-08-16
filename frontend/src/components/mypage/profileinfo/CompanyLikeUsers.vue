<template>
  <div class="like-user-box elevation-1">
    <v-row>
      <v-col v-for="(likeUser, i) in likeUsers" :key="i" cols="6">
        <LikeUserCard class="user-card elevation-2" :likeUser="likeUser" />
      </v-col>
    </v-row>
  </div>
</template>
<script>
import http from '@/http.js';
import LikeUserCard from './LikeUserCard.vue'

export default {
  data() {
    return {
      likeUsers: Array
    }
  },
  components: {
    LikeUserCard
  },
  mounted() {
    http.get('/company/userlike/' + this.$route.params.id)
    .then((res) => {
      this.likeUsers = res.data
    })
  },
}
</script>

<style>
.like-user-box {
  border: solid #eee 1px;
  border-radius: 4px;
  padding: 20px;
  height: 400px;
  width: 800px;
  overflow: auto;
  scroll-behavior: smooth;
  -ms-overflow-style: none;
  background-color: #EAF5F1;
}

.like-user-box::-webkit-scrollbar{ display: none;}

.user-card {
  border: 2px rgb(158, 158, 158) solid;
  border-radius: 4px;
  overflow: hidden;
  height: 100px;
}

</style>