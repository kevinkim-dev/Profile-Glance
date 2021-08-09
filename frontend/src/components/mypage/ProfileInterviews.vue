<template>
  <div class="profile-interview-box">
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