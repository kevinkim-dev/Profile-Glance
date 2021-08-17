<template>
  <div class="profile-infobutton-box">
    <div
      :style="this.infoStyle"
      @click="clickInfo"
      class="menu-button elevation-2"
    >
      {{this.firstButton}}
    </div>
    <div
      :style="this.wantedStyle"
      @click="clickWanteds"
      class="menu-button elevation-2"
      v-if="loginType == 'company'"
    >
      원티드 목록
    </div>
    <div
      :style="this.interviewStyle"
      @click="clickInterview"
      class="menu-button elevation-2"
    >
      면접 목록
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      firstButton: String,
      showInterview: false,
      infoStyle: {
        'background': '#C0DDD1',
      },
      wantedStyle: {
        'background': '#EAF5F1',
      },
      interviewStyle: {
        'background': '#EAF5F1',
      },
    }
  },
  methods: {
    clickInfo() {
      this.infoStyle.background='#C0DDD1'
      this.wantedStyle.background='#EAF5F1'
      this.interviewStyle.background='#EAF5F1'
      this.$emit('clickInfo')
    },
    clickWanteds() {
      this.wantedStyle.background='#C0DDD1'
      this.infoStyle.background='#EAF5F1'
      this.interviewStyle.background='#EAF5F1'
      this.$emit('clickWanteds')
    },
    clickInterview() {
      this.interviewStyle.background='#C0DDD1'
      this.wantedStyle.background='#EAF5F1'
      this.infoStyle.background='#EAF5F1'
      this.$emit('clickInterviews')
    },
  },
  computed: {
    loginType() {
      return localStorage.getItem('login_type')
    }
  },
  mounted() {
    if (localStorage.getItem('login_type') == 'user') {
      this.firstButton = '회원정보'
    } else {
      this.firstButton = '관심유저 목록'
    }
  }
}
</script>

<style>
.profile-infobutton-box {
  display: flex;
  flex-direction: column;
}

.menu-button {
  width: 44px;
  padding-left: 16px;
  padding-right: 14px;
  padding-top: 20px;
  padding-bottom: 20px;
  border-bottom-right-radius: 10px;
  border-top-right-radius: 10px;
  line-height: 18px;
  font-size: 14px
}

.menu-button:hover {
  cursor: pointer;
}
</style>