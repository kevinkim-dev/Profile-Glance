<template>
  <v-card class="px-5 py-5">
    <div class="d-flex justify-content-center py-3">
      <v-card-title>
        <span class="text-h4">회원정보 수정</span>
      </v-card-title>
    </div>
    <v-form v-model="valid">
      <v-container>
        <v-row>
          <v-col class="py-0 written" cols="12" md="6">
            이름: {{ this.$store.state.mypage.userData.userName }}
          </v-col>
          <v-col class="py-0 written" cols="12" md="6">
            생년월일: {{ this.$store.state.mypage.userData.birth }}
          </v-col>
          <v-col class="py-0 written" cols="12" md="6">
            닉네임: {{ this.$store.state.mypage.userData.userNickname }}
          </v-col>
          <v-col class="py-0 written" cols="12" md="6">
            이메일: {{ this.$store.state.mypage.userData.userEmail }}
          </v-col>
          <v-col class="py-0" cols="12">
            <v-text-field
              v-model="editForm.userPhone"
              :rules="phoneRules"
              label="휴대폰번호(01012345678)*"
              required
            ></v-text-field>
          </v-col>
          <v-col class="py-0" cols="12" md="6">
            <v-text-field
              v-model="editForm.major1"
              :rules="majorRules"
              label="전공1"
            ></v-text-field>
          </v-col>
          <v-col class="py-0" cols="12" md="6">
            <v-text-field
              v-model="editForm.major2"
              :rules="majorRules"
              label="전공2"
            ></v-text-field>
          </v-col>
          <v-col class="py-0" cols="12">
            <v-text-field
              v-model="editForm.portfolio1"
              :rules="portfolioRules"
              label="포트폴리오1"
            ></v-text-field>
          </v-col>
          <v-col class="py-0" cols="12">
            <v-text-field
              v-model="editForm.portfolio2"
              :rules="portfolioRules"
              label="포트폴리오2"
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
            <v-btn block text x-large class="primary-color text-white rounded-0" @click="edit">
              정보 수정
            </v-btn>
          </v-col>
          <v-col cols="6">
            <v-btn
              block
              text
              x-large
              class="secondary-color text-white rounded-0"
              @click="closeEditModal"
            >
              취소
            </v-btn>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
  </v-card>
</template>

<script>
import http from '@/http.js';
export default {
  data() {
    return {
      valid: false,
      phoneRules: [
        (v) => !!v || '필수 항목 입니다',
        (v) =>
          (!v.includes('.') && !v.includes('-') && v.startsWith('0')) || '올바른 형식이 아닙니다',
        (v) =>
          (/[0-9]/.test(v) && !/[a-zA-Z]/.test(v) && !/[~!@#$%^&*()_+|<>?:{}]/.test(v)) ||
          '숫자만 입력 가능합니다',
      ],
      majorRules: [(v) => v.length <= 16 || '최대 16자까지 입력 가능합니다'],
      portfolioRules: [(v) => v.length <= 100 || '최대 100자까지 입력 가능합니다'],
      editForm: {
        userEmail: this.$store.state.mypage.userData.userEmail,
        userPhone: '',
        major1: '',
        major2: '',
        portfolio1: '',
        portfolio2: '',
      },
    };
  },
  methods: {
    edit() {
      if (!this.valid) {
        alert('필수 항목을 입력해주세요.');
      } else {
        http.put('/user/update', this.editForm)
          .then(() => {
            alert('회원정보 수정이 완료되었습니다.');
            this.$store.dispatch('mypage/edit', this.editForm);
            this.editForm.userPhone = '';
            this.editForm.major1 = '';
            this.editForm.major2 = '';
            this.editForm.portfolio1 = '';
            this.editForm.portfolio2 = '';
            this.$emit('closeEditModal');
            // location.reload()
          })
          .catch((err) => {
            alert('회원정보 수정에 실패했습니다.');
            console.log(err);
          });
      }
    },
    closeEditModal() {
      this.editForm.userPhone = '';
      this.editForm.major1 = '';
      this.editForm.major2 = '';
      this.editForm.portfolio1 = '';
      this.editForm.portfolio2 = '';
      this.$emit('closeEditModal');
    },
  },
  mounted() {
    console.log(localStorage.getItem('user_info'))
    const userData = this.$store.state.mypage.userData
    this.editForm.userPhone = userData.userPhone
    this.editForm.major1 = userData.major1
    this.editForm.major2 = userData.major2
    this.editForm.portfolio1 = userData.portfolio1
    this.editForm.portfolio2 = userData.portfolio2
  }
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

.written {
  font-size: 18px;
  margin-top: 20px;
}
</style>
