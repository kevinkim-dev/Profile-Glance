<template>
<v-card>
  <div class="d-flex justify-content-center py-3">
    <v-card-title>
      <span class="text-h4">회원가입</span>
    </v-card-title>
  </div>
  <v-card-text>
    <v-container>
      <v-row>
        <v-col class="py-0" cols="12" md="6">
          <v-text-field
            v-model="loginForm.name"
            label="이름*"
            required
          ></v-text-field>
        </v-col>
        <v-col class="py-0" cols="12" md="6">
          <v-text-field
            v-model="loginForm.birthday"
            label="생년월일"
            type="date"
            required
          ></v-text-field>
        </v-col>
        <v-col class="py-0" cols="12" md="10">
          <v-text-field
            v-model="loginForm.nickname"
            label="닉네임*"
            required
          ></v-text-field>
        </v-col>
        <v-col class="py-0" cols="12" md="2">
          <v-btn text class="d-flex align-text-bottom font-weight-bold" style="color: #439474;">
            중복확인
          </v-btn>
        </v-col>
        <v-col class="py-0" cols="12" md="10">
          <v-text-field
            label="이메일*"
            required
          ></v-text-field>
        </v-col>
        <v-col class="py-0" cols="12" md="2">
          <v-btn text class="font-weight-bold" style="color: #439474;">
            중복확인
          </v-btn>
        </v-col>
        <v-col class="py-0" cols="12">
          <v-text-field
            v-model="loginForm.phone"
            label="휴대폰번호*"
            required
          ></v-text-field>
        </v-col>
        <v-col class="py-0" cols="12">
          <v-text-field
            v-model="loginForm.password"
            label="비밀번호*"
            type="password"
            required
          ></v-text-field>
        </v-col>
        <v-col class="py-0" cols="12">
          <v-text-field
            v-model="loginForm.passwordConfirm"
            label="비밀번호 확인*"
            type="password"
            required
          ></v-text-field>
        </v-col>
        <v-col class="py-0" cols="12" md="6">
          <v-text-field v-model="loginForm.major1" label="전공1"></v-text-field>
        </v-col>
        <v-col class="py-0" cols="12" md="6">
          <v-text-field v-model="loginForm.major2" label="전공2"></v-text-field>
        </v-col>
        <v-col class="py-1" cols="12">
          <large>*필수 입력 항목입니다</large>
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <v-col cols="6">
          <v-btn block text x-large class="primary-color text-white rounded-0" @click="submitSignUp">
            회원가입
          </v-btn>
        </v-col>
        <v-col cols="6">
          <v-btn block text x-large class="secondary-color text-white rounded-0" @click="$emit('close')">
            취소
          </v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="6">
          <p style="cursor: pointer" class="subtitle-1 text-center text-decoration-underline" @click="$emit('login')">
            이미 회원이신가요?
          </p>
        </v-col>
        <v-col cols="6">
          <p class="subtitle-1 text-center text-decoration-underline" @click="$emit('login')">
            기업 회원 가입은 메일로 문의주세요.
          </p>
        </v-col>
      </v-row>
    </v-container>
  </v-card-text>
</v-card>
</template>

<script>
export default {
  data () {
    return {
      loginForm: {
        name: '',
        nickname: '',
        birthday: '',
        email: '',
        phone: '',
        password: '',
        passwordConfirm: '',
        major1: '',
        major2: '',

      }
    }
  },
  methods: {
    submitSignUp () {
      console.log('회원가입')
    }
  }
}
</script>

<style>
.primary-color {
  background: #439474;
}
.secondary-color {
  background: #7D7D7D;
}
</style>