<template>
<v-card class="px-5">
  <div class="d-flex justify-content-center py-3">
    <v-card-title>
      <span class="text-h4">회원가입</span>
    </v-card-title>
  </div>
  <v-form v-model="valid">
    <v-container>
      <v-row>
        <v-col class="py-0" cols="12" md="6">
          <v-text-field
            v-model="loginForm.userName"
            :rules="nameRules"
            label="이름*"
            required
          ></v-text-field>
        </v-col>
        <v-col class="py-0" cols="12" md="6">
          <v-text-field
            v-model="loginForm.birth"
            :rules="birthRules"
            label="생년월일*"
            type="date"
            required
          ></v-text-field>
        </v-col>
        <v-col class="py-0" cols="12" md="10">
          <v-text-field
            v-model="loginForm.userNickname"
            :rules="nickNameRules"
            label="닉네임*"
            @input="resetDoubleCheck('nickName')"
            required
          ></v-text-field>
        </v-col>
        <v-col class="py-0" cols="12" md="2">
          <v-btn
            text
            class="d-flex align-text-bottom font-weight-bold"
            style="color: #439474;"
            @click="doubleCheck('nickName')"
            :disabled="nickNameDoubleCheckDisable">
            중복확인
          </v-btn>
        </v-col>
        <v-col class="py-0" cols="12" md="10">
          <v-text-field
            v-model="loginForm.userEmail"
            :rules="emailRules"
            label="아이디(example@example.com)*"
            @input="resetDoubleCheck('email')"
            required>
          </v-text-field>
        </v-col>
        <v-col class="py-0" cols="12" md="2">
          <v-btn
            text
            class="font-weight-bold"
            style="color: #439474;"
            @click="doubleCheck('email')"
            :disabled="emailDoubleCheckDisable">
            중복확인
          </v-btn>
        </v-col>
        <v-col class="py-0" cols="12">
          <v-text-field
            v-model="loginForm.userPhone"
            :rules="phoneRules"
            label="휴대폰번호(01012345678)*"
            required
          ></v-text-field>
        </v-col>
        <v-col class="py-0" cols="12">
          <v-text-field
            v-model="loginForm.userPassword"
            :rules="passwordRules"
            label="비밀번호(영문, 숫자, 특수문자 포함)*"
            type="password"
            required
          ></v-text-field>
        </v-col>
        <v-col class="py-0" cols="12">
          <v-text-field
            v-model="loginForm.passwordConfirm"
            :rules="passwordConfirmRules"
            label="비밀번호 확인*"
            type="password"
            required
          ></v-text-field>
        </v-col>
        <v-col class="py-0" cols="12" md="6">
          <v-text-field
            v-model="loginForm.major1"
            :rules="majorRules"
            label="전공1"
          ></v-text-field>
        </v-col>
        <v-col class="py-0" cols="12" md="6">
          <v-text-field
            v-model="loginForm.major2"
            :rules="majorRules"
            label="전공2"
          ></v-text-field>
        </v-col>
        <v-col class="py-1" cols="12">
          <large>*필수 입력 항목입니다</large>
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <v-col cols="6">
          <v-btn block text x-large class="primary-color text-white rounded-0" @click="signup">
            회원가입
          </v-btn>
        </v-col>
        <v-col cols="6">
          <v-btn block text x-large class="secondary-color text-white rounded-0" @click="closeSignUpModal">
            취소
          </v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="6">
          <p style="cursor: pointer;" class="subtitle-1 text-center go-to-login" @click="$emit('login')">
            이미 회원이신가요?
          </p>
        </v-col>
        <v-col cols="6">
          <p class="subtitle-1 text-center" style="line-height: 120%;" @click="$emit('login')">
            기업 회원 가입 문의
            profileglance@profileglance.com
          </p>
        </v-col>
      </v-row>
    </v-container>
  </v-form>
</v-card>
</template>

<script>
import Http from '@/http.js'
export default {
  data () {
    return {
      valid: false,
      nameRules: [
        v => !!v || '필수 항목 입니다',
        v => v.length >= 2 || '최소 2글자를 입력해주세요',
        v => v.length <= 30 || '최대 30자까지 입력 가능합니다',
      ],
      birthRules: [
        v => !!v || '필수 항목 입니다',
      ],
      nickNameRules: [
        v => !!v || '필수 항목 입니다',
        v => v.length >= 2 || '최소 2글자를 입력해야 합니다',
        v => v.length <= 30 || '최대 30자까지 입력 가능합니다',
      ],
      emailRules: [
        v => !!v || '필수 항목 입니다',
        v => (v.includes('@') && v.includes('.')) || '올바른 형식이 아닙니다',

      ],
      phoneRules: [
        v => !!v || '필수 항목 입니다',
        v => !v.includes('.') && !v.includes('-') && v.startsWith('0') || '올바른 형식이 아닙니다',
        v => /[0-9]/.test(v) && !/[a-zA-Z]/.test(v) && !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || '숫자만 입력 가능합니다'
      ],
      passwordRules: [
        v => !!v || '필수 항목 입니다',
        v => v.length >= 9 || '최소 9글자를 입력해야 합니다',
        v => v.length <= 16 || '최대 16자까지 입력 가능합니다',
        v => /[0-9]/.test(v) && /[a-zA-Z]/.test(v) && /[~!@#$%^&*()_+|<>?:{}]/.test(v) || '비밀번호는 영문, 숫자, 특수문자가 조합되어야 합니다'
      ],
      passwordConfirmRules: [
        v => !!v || '필수 항목 입니다',
        v => v === this.loginForm.userPassword || '입력한 비밀번호와 일치하지 않습니다',
      ],
      majorRules: [
        v => v.length <= 16 || '최대 16자까지 입력 가능합니다'
      ],
      loginForm: {
        userName: '',
        userNickname: '',
        birth: '',
        userEmail: '',
        userPhone: '',
        userPassword: '',
        passwordConfirm: '',
        major1: '',
        major2: '',
      },
      isNickNameDoubleChecked: false,
      isEmailDoubleChecked: false,
    }
  },
  computed: {
    nickNameDoubleCheckDisable () {
      if (this.isNickNameDoubleChecked) {
        return true
      } else {
        return false
      }
    },
    emailDoubleCheckDisable () {
      if (this.isEmailDoubleChecked) {
        return true
      } else {
        return false
      }
    }
  },
  methods: {
    // 회원가입
    signup () {
      if (!this.valid) {
        Swal.fire({ 
          icon: 'warning', // Alert 타입 
          title: '필수 항목을 입력해주세요.', // Alert 제목 
          text: '필수 항목을 모두 입력해야 회원가입이 가능합니다.', // Alert 내용 
        });
        // alert('필수 항목을 입력해주세요.')
      }
      else if (!this.isNickNameDoubleChecked) {
        Swal.fire({ 
          icon: 'warning', // Alert 타입 
          title: '닉네임 중복 확인을 해주세요.', // Alert 제목 
          text: '닉네임 중복 확인 후 회원가입이 가능합니다.', // Alert 내용 
        });
        // alert('닉네임 중복 확인을 해주세요.')
      }
      else if (!this.isEmailDoubleChecked) {
        Swal.fire({ 
          icon: 'warning', // Alert 타입 
          title: '아이디 중복 확인을 해주세요.', // Alert 제목 
          text: '아이디 중복 확인 후 회원가입이 가능합니다.', // Alert 내용 
        });
        // alert('아이디 중복 확인을 해주세요.')
      }
      else {
        Http.post('/user/signup', this.loginForm)
        .then(() => {
          Swal.fire({ 
            icon: 'success', // Alert 타입 
            title: '회원가입이 완료되었습니다.', // Alert 제목 
            text: '!환영합니다! 로그인해 주세요:)', // Alert 내용 
          });
          // alert('회원가입이 완료되었습니다.')
          this.loginForm.userName = ''
          this.loginForm.userNickname = ''
          this.loginForm.birth = ''
          this.loginForm.userEmail = ''
          this.loginForm.userPhone = ''
          this.loginForm.userPassword = ''
          this.loginForm.passwordConfirm = ''
          this.loginForm.major1 = ''
          this.loginForm.major2 = ''
          this.$emit('close')
        })
        .catch((err) => {
          Swal.fire({ 
            icon: 'error', // Alert 타입 
            title: '회원가입에 실패했습니다.', // Alert 제목 
            text: '다시 회원가입해 주세요', // Alert 내용 
          });
          // alert('회원가입에 실패했습니다.')
          console.log(err)
        })
      }
    },
    // 닉네임&이메일 중복확인
    doubleCheck (target) {
      if (target === 'nickName') {
        if (this.loginForm.userNickname) {
          const url = '/user/usernicknamecheck/' + this.loginForm.userNickname
          Http.get(url)
          .then((res) => {
            if (res.status === 201) {
              this.isNickNameDoubleChecked = true
              Swal.fire({ 
                icon: 'success', // Alert 타입 
                title: '사용할 수 있는 닉네임입니다.', // Alert 제목 
                text: '입력한 닉네임을 사용합니다.', // Alert 내용 
              });
              // alert('사용할 수 있는 닉네임입니다.')
            } else if (res.status === 202) {
              this.isNickNameDoubleChecked = false
              Swal.fire({ 
                icon: 'error', // Alert 타입 
                title: '중복된 닉네임입니다.', // Alert 제목 
                text: '입력한 닉네임을 사용할 수 없습니다.', // Alert 내용 
              });
              // alert('중복된 닉네임입니다.')
            }
          })
          .catch((err) => {
            console.log(err)
          })
        } else {
          this.isNickNameDoubleChecked = false
          Swal.fire({ 
            icon: 'warning', // Alert 타입 
            title: '닉네임을 입력해주세요.', // Alert 제목 
            text: '닉네임 체크를 위해 닉네임을 입력해 주세요.', // Alert 내용 
          });
          // alert('닉네임을 입력해주세요.')
        }
      } else if (target === 'email') {
        if (this.loginForm.userEmail) {
          const url = '/user/useremailcheck/' + this.loginForm.userEmail
          Http.get(url)
          .then((res) => {
            if (res.status === 201) {
              this.isEmailDoubleChecked = true
              Swal.fire({ 
                icon: 'success', // Alert 타입 
                title: '사용할 수 있는 아이디입니다.', // Alert 제목 
                text: '입력한 아이디를 사용합니다.', // Alert 내용 
              });
              // alert('사용할 수 있는 아이디입니다.')
            } else if (res.status === 202) {
              this.isEmailDoubleChecked = false
              Swal.fire({ 
                icon: 'error', // Alert 타입 
                title: '중복된 아이디입니다.', // Alert 제목 
                text: '입력한 아이디를 사용할 수 없습니다.', // Alert 내용 
              });
              // alert('중복된 아이디입니다.')
            }
          })
          .catch((err) => {
            console.log(err)
          })
        } else {
          this.isEmailDoubleChecked = false
          Swal.fire({ 
            icon: 'warning', // Alert 타입 
            title: '아이디를 입력해주세요.', // Alert 제목 
            text: '아이디 체크를 위해 아이디를 입력해 주세요.', // Alert 내용 
          });
          // alert('아이디를 입력해주세요.')
        }
      }
    },
    // 중복확인 여부 false로 리셋
    resetDoubleCheck (target) {
      if (target === 'nickName') {
        this.isNickNameDoubleChecked = false
      } else if (target === 'email') {
        this.isEmailDoubleChecked = false
      }
    },
    // 회원가입 모달 닫힘
    closeSignUpModal () {
      this.loginForm.userName = ''
      this.loginForm.userNickname = ''
      this.loginForm.birth = ''
      this.loginForm.userEmail = ''
      this.loginForm.userPhone = ''
      this.loginForm.userPassword = ''
      this.loginForm.passwordConfirm = ''
      this.loginForm.major1 = ''
      this.loginForm.major2 = ''
      this.$emit('close')
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
.go-to-login:hover {
  text-decoration: underline;
}
</style>
