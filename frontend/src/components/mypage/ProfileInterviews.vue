<template>
  <div class="profile-interview-box">
      <!-- <ul class="p-3">
        <li>네이버 | 07.25 13:00 | <button>들어가기</button></li>
        <li>쿠팡 | 07.27 15:00 | <button>들어가기</button></li>
        <li>카카오 | 07.30 17:00 | <button>들어가기</button></li>
      </ul> -->
      <ul v-for="(userInterview, idx) in userInterviews" :key="idx">
        <UserInterview :userInterview="userInterview" />
      </ul>
  </div>
</template>

<script>
import UserInterview from './UserInterview.vue'

export default {
  components: {
    UserInterview
  },
  computed: {
    userInterviews() {
      return this.$store.state.mypage.userInterviews
    }
  }
}
</script>

<style>
.profile-interview-box {
  background-color: #eee;
  padding: 30px;
  height: 100%;
}
</style>