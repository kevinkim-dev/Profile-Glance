<template>
  <div>
    <v-card class="container mb-5">
      <div class="d-flex justify-content-center">
        <v-card-title>
          <span class="text-h4">기업 회원가입</span>
        </v-card-title>
      </div>
      <v-row class="d-flex justify-content-center">
        <v-col cols="3" class="company-logo-form">
          <div class="company-logo-box">
            <img class="company-logo" :src="imgSrc" alt="logo.png" />
          </div>
          <div class="filebox company-logo-button">
            <label for="company_logo">사진 편집</label>
            <input
              type="file"
              id="company_logo"
              @change="changeImage"
              ref="img"
              accept="img/*"
              multiple
            />
          </div>
        </v-col>
        <v-col cols="6">
          <v-form v-model="valid">
            <v-container>
              <v-row>
                <v-col class="py-0" cols="12" md="12">
                  <v-text-field
                    v-model="companySignupForm.companyName"
                    :rules="nameRules"
                    label="이름*"
                    required
                  ></v-text-field>
                </v-col>
                <v-col class="py-0" cols="12" md="10">
                  <v-text-field
                    v-model="companySignupForm.companyId"
                    :rules="idRules"
                    label="아이디*"
                    @input="resetDoubleCheck()"
                    required
                  >
                  </v-text-field>
                </v-col>
                <v-col class="py-0" cols="12" md="2">
                  <v-btn
                    text
                    class="font-weight-bold"
                    style="color: #439474;"
                    @click="doubleCheck()"
                    :disabled="idDoubleCheckDisable"
                  >
                    중복확인
                  </v-btn>
                </v-col>
                <v-col class="py-0" cols="12">
                  <v-text-field
                    v-model="companySignupForm.companyPassword"
                    :rules="passwordRules"
                    label="비밀번호(영문, 숫자, 특수문자 포함)*"
                    type="password"
                    required
                  ></v-text-field>
                </v-col>
                <v-col class="py-0" cols="12">
                  <v-text-field
                    v-model="companySignupForm.passwordConfirm"
                    :rules="passwordConfirmRules"
                    label="비밀번호 확인*"
                    type="password"
                    required
                  ></v-text-field>
                </v-col>
                <v-col class="py-0" cols="12" md="12">
                  <v-text-field
                    v-model="companySignupForm.companyEmail"
                    :rules="emailRules"
                    label="이메일(example@example.com)*"
                    required
                  >
                  </v-text-field>
                </v-col>
                <v-col class="py-0" cols="12">
                  <v-text-field
                    v-model="companySignupForm.companyPhone"
                    :rules="phoneRules"
                    label="휴대폰번호(01012345678)*"
                    required
                  ></v-text-field>
                </v-col>
                <v-col class="py-1" cols="12">
                  *필수 입력 항목입니다
                </v-col>
              </v-row>
            </v-container>
          </v-form>
        </v-col>
      </v-row>
      <v-container>
        <v-row class="d-flex justify-content-center mt-5">
          <v-col cols="4">
            <v-btn
              block
              text
              x-large
              class="primary-color text-white rounded-0"
              @click="companySignup"
            >
              회원가입
            </v-btn>
          </v-col>
          <v-col cols="4">
            <v-btn
              block
              text
              x-large
              class="secondary-color text-white rounded-0"
              @click="closeCompanySignUp"
            >
              취소
            </v-btn>
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </div>
</template>

<script>
import Axios from 'axios';
import Http from '@/http.js';
export default {
  data() {
    return {
      imgSrc: 'images/mypage/noimage.png',
      valid: false,
      nameRules: [
        (v) => !!v || '필수 항목 입니다',
        (v) => v.length >= 2 || '최소 2글자를 입력해주세요',
        (v) => v.length <= 30 || '최대 30자까지 입력 가능합니다',
      ],
      idRules: [
        (v) => !!v || '필수 항목 입니다',
        (v) => v.length >= 2 || '최소 2글자를 입력해주세요',
        (v) => v.length <= 30 || '최대 30자까지 입력 가능합니다',
      ],
      passwordRules: [
        (v) => !!v || '필수 항목 입니다',
        (v) => v.length >= 9 || '최소 9글자를 입력해야 합니다',
        (v) => v.length <= 16 || '최대 16자까지 입력 가능합니다',
        (v) =>
          (/[0-9]/.test(v) && /[a-zA-Z]/.test(v) && /[~!@#$%^&*()_+|<>?:{}]/.test(v)) ||
          '비밀번호는 영문, 숫자, 특수문자가 조합되어야 합니다',
      ],
      passwordConfirmRules: [
        (v) => !!v || '필수 항목 입니다',
        (v) =>
          v === this.companySignupForm.companyPassword || '입력한 비밀번호와 일치하지 않습니다',
      ],
      emailRules: [
        (v) => !!v || '필수 항목 입니다',
        (v) => (v.includes('@') && v.includes('.')) || '올바른 형식이 아닙니다',
      ],
      phoneRules: [
        (v) => !!v || '필수 항목 입니다',
        (v) =>
          (!v.includes('.') && !v.includes('-') && v.startsWith('0')) || '올바른 형식이 아닙니다',
        (v) =>
          (/[0-9]/.test(v) && !/[a-zA-Z]/.test(v) && !/[~!@#$%^&*()_+|<>?:{}]/.test(v)) ||
          '숫자만 입력 가능합니다',
      ],
      companySignupForm: {
        companyName: '',
        companyId: '',
        companyPassword: '',
        passwordConfirm: '',
        companyEmail: '',
        companyPhone: '',
        companyImg: File,
      },
      isIdDoubleChecked: false,
    };
  },
  computed: {
    idDoubleCheckDisable() {
      if (this.isIdDoubleChecked) {
        return true;
      } else {
        return false;
      }
    },
  },
  methods: {
    // 회원가입
    companySignup() {
      if (!this.valid) {
        alert('필수 항목을 입력해주세요.');
      } else if (!this.isIdDoubleChecked) {
        alert('닉네임 중복 확인을 해주세요.');
      } else {
        const formData = new FormData();

        formData.append('companyId', this.companySignupForm.companyId);
        formData.append('companyName', this.companySignupForm.companyName);
        formData.append('companyEmail', this.companySignupForm.companyEmail);
        formData.append('companyPassword', this.companySignupForm.companyPassword);
        formData.append('companyPhone', this.companySignupForm.companyPhone);
        formData.append('companyImg', this.companySignupForm.companyImg);

        Axios.post('company/signup', formData, {
          // baseURL: "http://localhost:8888/",
          baseURL: 'http://52.79.113.173:8888/',
          headers: { 'Content-Type': 'multipart/form-data' },
        })
          .then((res) => {
            console.log(res);
            alert('기업회원 추가가 완료되었습니다.');
            this.companySignupForm.companyName = '';
            this.companySignupForm.companyId = '';
            this.companySignupForm.companyPassword = '';
            this.companySignupForm.passwordConfirm = '';
            this.companySignupForm.companyEmail = '';
            this.companySignupForm.companyPhone = '';
            this.$router.push('mypage');
          })
          .catch((err) => {
            alert('기업회원 추가가 실패했습니다.');
            console.log(err);
          });
      }
    },
    // 아이디 중복확인
    doubleCheck() {
      if (this.companySignupForm.companyId) {
        const url = '/company/companyidcheck/' + this.companySignupForm.companyId;
        Http.get(url)
          .then((res) => {
            if (res.status === 201) {
              this.isIdDoubleChecked = true;
              alert('사용할 수 있는 아이디입니다.');
            } else if (res.status === 202) {
              this.isIdDoubleChecked = false;
              alert('중복된 아이디입니다.');
            }
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        this.isIdDoubleChecked = false;
        alert('닉네임을 입력해주세요.');
      }
    },
    // 중복확인 여부 false로 리셋
    resetDoubleCheck() {
      this.isIdDoubleChecked = false;
    },
    closeCompanySignUp() {
      this.companySignupForm.companyName = '';
      this.companySignupForm.companyId = '';
      this.companySignupForm.companyPassword = '';
      this.companySignupForm.passwordConfirm = '';
      this.companySignupForm.companyEmail = '';
      this.companySignupForm.companyPhone = '';
      this.$router.push('mypage');
    },
    changeImage(e) {
      const file = e.target.files[0];
      this.companySignupForm.companyImg = file;
      this.imgSrc = URL.createObjectURL(file);
    },
  },
};
</script>

<style>
.primary-color {
  background: #439474;
}
.secondary-color {
  background: #7d7d7d;
}
.go-to-login:hover {
  text-decoration: underline;
}

.company-signup-image-form {
  border: black 1px solid;
}

.company-logo-box {
  position: relative;
  height: 200px;
  width: 200px;
  border-radius: 70%;
  overflow: hidden;
  border: #439474 1px solid;
}

.company-logo {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.company-logo-form {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.company-logo-button {
  position: relative;
  left: 50px;
  top: 50px;
  width: 100px;
}
</style>
