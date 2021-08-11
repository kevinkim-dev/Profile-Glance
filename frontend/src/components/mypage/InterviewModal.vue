<template>
  <v-card class="px-5 py-5">
    <div class="d-flex justify-content-center py-3">
      <v-card-title>
        <span class="text-h4">면접 신청</span>
      </v-card-title>
    </div>
    <v-form v-model="valid">
      <v-container>
        <v-row>
          <v-row>
            <v-col cols="6">
              <v-menu
                v-model="descriptionCalendar"
                :close-on-content-click="false"
                :nudge-right="40"
                transition="scale-transition"
                offset-y
                min-width="auto"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="interviewDate"
                    label="면접 날짜"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-model="interviewDate"
                  :rules="dateRules"
                  @input="descriptionCalendar = false"
                ></v-date-picker>
              </v-menu>
            </v-col>
            <v-col cols="6">
              <v-text-field type="time" v-model="interviewTime" label="면접 시작 시각"
              :rules="timeRules"
              >
              </v-text-field>
            </v-col>
          </v-row>
          <v-col class="py-1" cols="12">
            <large>*필수 입력 항목입니다</large>
          </v-col>
        </v-row>
      </v-container>
      <v-container>
        <v-row>
          <v-col cols="6">
            <v-btn block text x-large class="primary-color text-white rounded-0" @click="registInterview">
              면접 신청
            </v-btn>
          </v-col>
          <v-col cols="6">
            <v-btn
              block
              text
              x-large
              class="secondary-color text-white rounded-0"
              @click="closeInterviewModal"
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
import { mapGetters } from 'vuex'

export default {
  data() {
    return {
      valid: false,
      dateRules: [(v) => !!v || '필수 항목 입니다'],
      timeRules: [(v) => !!v || '필수 항목 입니다'],
      interviewDate: '',
      interviewTime: '',
      descriptionCalendar: false,
    };
  },
  computed: {
    ...mapGetters([
        'fileURL',
      ]),
  },
  methods: {
    registInterview() {
      if (!this.valid) {
        if (!this.interviewDate) {
          alert('면접 날짜를 선택해주세요.');
        } else if (!this.interviewTime) {
          alert('면접 시간을 선택해주세요.');
        }
      } else {
        //인터뷰 신청 axios 
        const body = {
          'companyId': localStorage.getItem('id'),
          'interviewDate': this.interviewDate,
          'interviewTime': this.interviewTime,
          'userNickname': this.$route.params.id
        }
        console.log(body)
        http.post('/interview/createinterview', body)
        .then(res => {
          alert('성공')
          location.reload()
        })
        .catch(err => {
          console.log(err)
        })
      }
    },
    closeInterviewModal() {
      this.interviewDate = '';
      this.interviewTime = '';
      this.$emit('closeInterviewModal');
    },
    dateCalendarClose() {
      if (this.recruitDate.length === 2) {
        this.dateCalendar = false;
      }
    },
    refreshAll() {
      this.$router.go()
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

.written {
  font-size: 18px;
  margin-top: 20px;
}

.interviewee-image-box {
  position: relative;
  height: 120px;
  width: 120px;
  /* left: 70px; */
  border-radius: 70%;
  overflow: hidden;
}

.interviewee-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.interviewee-info-box {
  position: relative;
}
</style>
