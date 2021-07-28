<template>
  <v-card>
    <v-card-title class="d-flex justify-content-center p-5">
      <span class="text-h4">로그인</span>
    </v-card-title>
    <v-card-text>
      <v-container>
        <v-row>
          <v-col cols="12" class="py-0">
            <v-text-field
              label="아이디(이메일)"
              required
              v-model="user_email"
            ></v-text-field>
          </v-col>
          <v-col cols="12" class="py-0">
            <v-text-field
              label="비밀번호"
              type="password"
              required
              v-model="user_password"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row class="text-center mb-1">
          <v-col cols="6">
            <v-btn class="login-button rounded-0 mb-5"
              block
              x-large
              color="white"
              text
              @click="$emit('close')"
            >
              로그인
            </v-btn>
            <u @click="$emit('close')">아차차! 아이디를 잊으셨나요?</u>
          </v-col>
          <v-col cols="6">
            <v-btn class="close-button rounded-0 mb-5"
              block
              x-large
              color="white"
              text
              @click="closeLoginModal"
            >
              취소
            </v-btn>
            <u @click="$emit('close')">아차! 비밀번호를 잊으셨나요?</u>
          </v-col>
        </v-row>
        <v-card-text class="text-center">
          <u class="signup" @click="$emit('signup')">이런... 아직도 회원이 아니신가요?</u>
        </v-card-text>
      </v-container>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  data: function() {
    return {
      user_email: '',
      user_password: ''
    }
  },
  methods: {
    closeLoginModal() {
      this.user_email = ''
      this.user_password = ''
      return this.$emit('close')
    }
  }
}
</script>

<style>
.login-button {
  background: #439474;
}

.close-button {
  background: #7D7D7D;
}


u:hover {
  cursor: pointer;
}

.forget {
  display: flex;
  justify-content: space-evenly;
}

.signup {
  font-size: 16px;
  font-weight: bold;
}
</style>