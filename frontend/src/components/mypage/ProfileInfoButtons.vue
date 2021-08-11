<template>
  <div class="profile-infobutton-box">
    <v-btn class="pg-button me-1"
      color="white"
      text
      @click="clickInfo"
      :style="this.infoStyle"
    >
      회원 정보
    </v-btn>
    <v-btn class="pg-button me-1"
      color="white"
      text
      @click="clickWanteds"
      :style="this.wantedStyle"
      v-if="loginType == 'company'"
    >
      원티드 목록
    </v-btn>
    <v-btn class="pg-button"
      color="white"
      text
      @click="clickInterview"
      :style="this.interviewStyle"
    >
      면접 목록
    </v-btn>
  </div>
</template>

<script>
export default {
  data() {
    return {
      showInterview: false,
      infoStyle: {
        background: '#439474'
      },
      wantedStyle: {
        background: '#C0DDD1'
      },
      interviewStyle: {
        background: '#C0DDD1'
      },
    }
  },
  methods: {
    clickInfo() {
      this.infoStyle.background='#439474'
      this.wantedStyle.background='#C0DDD1'
      this.interviewStyle.background='#C0DDD1'
      this.$emit('clickInfo')
    },
    clickWanteds() {
      this.wantedStyle.background='#439474'
      this.infoStyle.background='#C0DDD1'
      this.interviewStyle.background='#C0DDD1'
      this.$emit('clickWanteds')
    },
    clickInterview() {
      this.interviewStyle.background='#439474'
      this.wantedStyle.background='#C0DDD1'
      this.infoStyle.background='#C0DDD1'
      this.$emit('clickInterviews')
    },
  },
  computed: {
    loginType() {
      return localStorage.getItem('login_type')
    }
  }

}
</script>

<style>
.profile-infobutton-box {
  width: 800px;
  display: flex;
  justify-content: flex-end;
}

.pg-button {
    width: 130px;
}

</style>