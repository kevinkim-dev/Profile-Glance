<template>
  <div>
    <v-card v-if="loginType == 'company'">
      <v-card-title class="d-flex justify-content-between p-5">
        <span class="text-h4">기업 로그인</span>
        <v-btn @click="userLogin">일반회원으로 로그인</v-btn>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12" class="py-0">
              <v-text-field label="아이디" required v-model="companyId"></v-text-field>
            </v-col>
            <v-col cols="12" class="py-0">
              <v-text-field
                label="비밀번호"
                type="password"
                required
                v-model="companyPassword"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row class="text-center mb-1">
            <v-col cols="6">
              <v-btn
                class="rounded-0 mb-5"
                :disabled="company_login_button_disable"
                :style="company_login_button_style"
                block
                x-large
                color="white"
                text
                @click="clickLogin"
              >
                로그인
              </v-btn>
              <!-- <u @click="$emit('close')">아차차! 아이디를 잊으셨나요?</u> -->
            </v-col>
            <v-col cols="6">
              <v-btn
                class="close-button rounded-0 mb-5"
                block
                x-large
                color="white"
                text
                @click="closeLoginModal"
              >
                취소
              </v-btn>
              <!-- <u @click="$emit('close')">아차! 비밀번호를 잊으셨나요?</u> -->
            </v-col>
          </v-row>
          <v-card-text class="text-center">
            <u class="signup" @click="openSignUpModal">이런... 아직도 회원이 아니신가요?</u>
          </v-card-text>
        </v-container>
      </v-card-text>
    </v-card>
    <v-card v-else-if="loginType == 'user'">
      <v-card-title class="d-flex justify-content-between p-5">
        <span class="text-h4">로그인</span>
        <v-btn @click="companyLogin">기업회원으로 로그인</v-btn>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12" class="py-0">
              <v-text-field label="아이디(이메일)" required v-model="user_email"></v-text-field>
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
              <v-btn
                class="rounded-0 mb-5"
                :disabled="login_button_disable"
                :style="login_button_style"
                block
                x-large
                color="white"
                text
                @click="clickLogin"
              >
                로그인
              </v-btn>
              <!-- <u @click="$emit('close')">아차차! 아이디를 잊으셨나요?</u> -->
            </v-col>
            <v-col cols="6">
              <v-btn
                class="close-button rounded-0 mb-5"
                block
                x-large
                color="white"
                text
                @click="closeLoginModal"
              >
                취소
              </v-btn>
              <!-- <u @click="$emit('close')">아차! 비밀번호를 잊으셨나요?</u> -->
            </v-col>
          </v-row>
          <v-card-text class="text-center">
            <u class="signup" @click="openSignUpModal">이런... 아직도 회원이 아니신가요?</u>
          </v-card-text>
        </v-container>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import Http from '@/http.js';

export default {
  data: function() {
    return {
      user_email: '',
      user_password: '',
      loginType: 'user',
      companyId: '',
      companyPassword: '',
    };
  },
  computed: {
    login_button_style() {
      if (!this.user_email.includes('.') || !this.user_email.includes('@') || !this.user_password) {
        return {
          background: '#a2e2c8',
        };
      } else {
        return {
          background: '#439474',
        };
      }
    },
    login_button_disable() {
      if (!this.user_email.includes('.') || !this.user_email.includes('@') || !this.user_password) {
        return true;
      } else {
        return false;
      }
    },
    company_login_button_style() {
      if (!this.companyId || !this.companyPassword) {
        return {
          background: '#a2e2c8',
        };
      } else {
        return {
          background: '#439474',
        };
      }
    },
    company_login_button_disable() {
      if (!this.companyId || !this.companyPassword) {
        return true;
      } else {
        return false;
      }
    },
  },
  methods: {
    closeLoginModal() {
      this.user_email = '';
      this.user_password = '';
      this.companyId = '';
      this.companyPassword = '';
      return this.$emit('close');
    },
    openSignUpModal() {
      this.user_email = '';
      this.user_password = '';
      return this.$emit('signup');
    },
    async clickLogin() {
      if (this.loginType == 'user') {
        if (!this.user_email) {
          return alert('이메일을 입력해주세요.');
        } else if (!this.user_password) {
          return alert('비밀번호를 입력해주세요.');
        } else {
          const body = {
            userEmail: this.user_email,
            userPassword: this.user_password,
          };
          await Http.post('/user/login', body)
            .then(async (res) => {
              localStorage.setItem('token', res.data);
              localStorage.setItem('login_type', this.loginType);
              localStorage.setItem('user_email', this.user_email);
              await Http.get('/user/myinfo/' + localStorage.getItem('user_email'))
                .then((res) => {
                  localStorage.setItem('id', res.data.userNickname);
                  localStorage.setItem('profile', res.data.userImg);
                  if (res.data.admin) {
                    localStorage.setItem('login_type', 'admin');
                    localStorage.setItem('id', '관리자');
                  }
                })
                .catch((err) => {
                  alert(err);
                });
              this.$router.push('lookatme');
              location.reload();
            })
            .catch((err) => {
              Swal.fire({ 
                icon: 'warning', // Alert 타입 
                title: '아이디 / 비밀번호가\n 맞지 않습니다.', // Alert 제목 
                text: '아이디 / 비밀번호를 다시 확인해주세요.', // Alert 내용 
              });
              // console.log('catch');
              // console.log(err);
            });
        }
      } else if (this.loginType == 'company') {
        if (!this.companyId) {
          return alert('아이디를 입력해주세요.');
        } else if (!this.companyPassword) {
          return alert('비밀번호를 입력해주세요.');
        } else {
          const body = {
            companyId: this.companyId,
            companyPassword: this.companyPassword,
          };
          await Http.post('/company/login', body)
            .then(async (res) => {
              localStorage.setItem('token', res.data);
              localStorage.setItem('login_type', this.loginType);
              localStorage.setItem('id', this.companyId);
              this.$store.dispatch('getLikeUserList', this.companyId);
              await Http.get('/company/companyinfo/' + this.companyId)
              .then((res) => {
                localStorage.setItem('profile', res.data.companyImg)
                localStorage.setItem('name', res.data.companyName)
              })
              .catch((err) => {
                alert(err);
              });
              this.$router.push('lookatme');
              location.reload();
            })
            .catch((err) => {
              Swal.fire({ 
                icon: 'warning', // Alert 타입 
                title: '아이디 / 비밀번호가\n 맞지 않습니다.', // Alert 제목 
                text: '아이디 / 비밀번호를 다시 확인해주세요.', // Alert 내용 
              });
              // console.log('catch');
              // console.log(err);
            });
        }
      }
    },
    companyLogin() {
      this.loginType = 'company';
    },
    userLogin() {
      this.loginType = 'user';
    },
  },
};
</script>

<style>
.login-button {
  background: #439474;
  /* background: #a2e2c8; */
}

.close-button {
  background: #7d7d7d;
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

.swal2-popup {
  font-size: 0.8rem;
}
</style>