<template>
  <div class="pt-1">
    <v-form v-model="valid">
      <h1 align="center" class="my-4">채용공고 등록</h1>
      <v-container style="max-width: 800px; background: #EAF5F1;">
        <v-row class="justify-center">
          <br />
          <v-col cols="12">
            <v-row>
              <v-col cols="6">
                <v-select
                  label="모집 직무"
                  :items="jobs"
                  :rules="jobRules"
                  v-model="registRecruitForm.job"
                >
                </v-select>
              </v-col>
              <v-col cols="6">
                <v-text-field label="모집 분야" v-model="registRecruitForm.jobDetail">
                </v-text-field>
              </v-col>
            </v-row>
            <v-row class="d-flex align-center">
              <v-col cols="6">
                <v-radio-group
                  row
                  v-model="registRecruitForm.career"
                  :rules="careerRules"
                  label="신입/경력"
                >
                  <v-radio label="신입" value="신입"></v-radio>
                  <v-radio label="경력" value="경력"></v-radio>
                </v-radio-group>
              </v-col>
              <v-col cols="6">
                <v-menu
                  v-model="dateCalendar"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  transition="scale-transition"
                  offset-y
                  min-width="auto"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field
                      v-model="recruitDate.join(' ~ ')"
                      label="모집 기간"
                      prepend-icon="mdi-calendar"
                      readonly
                      v-bind="attrs"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker
                    v-model="recruitDate"
                    range
                    @input="dateCalendarClose"
                  ></v-date-picker>
                </v-menu>
              </v-col>
            </v-row>
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
                      v-model="pptDate"
                      label="설명회 날짜"
                      prepend-icon="mdi-calendar"
                      readonly
                      v-bind="attrs"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker
                    v-model="pptDate"
                    @input="descriptionCalendar = false"
                  ></v-date-picker>
                </v-menu>
              </v-col>
              <v-col cols="6">
                <v-text-field type="time" v-model="pptTime" label="설명회 시작 시각">
                </v-text-field>
              </v-col>
            </v-row>
            <v-text-field
              label="기업소개 url"
              :rules="descriptionURLRules"
              v-model="registRecruitForm.descriptionURL"
            >
            </v-text-field>
            <v-text-field
              label="채용공고 url"
              :rules="recruitURLRules"
              v-model="registRecruitForm.recruitURL"
            >
            </v-text-field>
          </v-col>
        </v-row>
      </v-container>
      <div style="height: 200px;" class="py-4">
        <v-row class="d-flex justify-center">
          <v-col cols="2">
            <v-btn
              block
              text
              x-large
              class="primary-color text-white rounded-0"
              @click="registRecruit"
            >
              등록
            </v-btn>
          </v-col>
          <v-col cols="2">
            <v-btn
              block
              text
              x-large
              class="secondary-color text-white rounded-0"
              @click="cancel()"
            >
              취소
            </v-btn>
          </v-col>
        </v-row>
      </div>
    </v-form>
  </div>
</template>

<script>
import router from '@/router';
import Http from '@/http.js';
export default {
  name: 'WantedRegist',
  data: function() {
    return {
      valid: false,
      jobRules: [(v) => !!v || '필수 항목 입니다'],
      careerRules: [(v) => !!v || '필수 항목 입니다'],
      descriptionURLRules: [(v) => !!v || '필수 항목 입니다'],
      recruitURLRules: [(v) => !!v || '필수 항목 입니다'],
      jobs: [
        'IT',
        '마케팅',
        '영업',
        '경영지원',
        '디자인',
        '서비스',
        '전문직',
        '의류',
        '생산제조',
        '건설',
        '유통무역',
        '미디어',
        '교육',
        '특수계층/공공',
        '연구직',
      ],
      recruitDate: [],
      pptDate: '',
      pptTime: '',
      registRecruitForm: {
        career: '',
        companyId: localStorage.getItem('id'),
        // companyId: this.$store.state.data.companyData.companyId,
        descriptionURL: '',
        job: '',
        jobDetail: '',
        presentationDate: '',
        recruitStartDate: '',
        recruitEndDate: '',
        recruitURL: '',
      },
      startDateCalendar: false,
      dateCalendar: false,
      descriptionCalendar: false,
    };
  },
  watch: {
    recruitDate() {
      if (this.recruitDate.length >= 1) {
        this.registRecruitForm.recruitStartDate = this.recruitDate[0];
      }
      if (this.recruitDate.length >= 2) {
        this.registRecruitForm.recruitEndDate = this.recruitDate[1];
      }
    },
    pptTime() {
      if (this.pptTime) {
        this.registRecruitForm.presentationDate = this.pptDate + ' ' + this.pptTime;
      }
    },
  },
  methods: {
    registRecruit() {
      if (!this.valid) {
        if (!this.registRecruitForm.job) {
          Swal.fire({ 
            icon: 'warning', // Alert 타입 
            title: '모집직무를 선택해주세요.', // Alert 제목 
            text: '필수 항목을 모두 입력해야 등록이 가능합니다.', // Alert 내용 
          });
          // alert('모집직무를 선택해주세요.');
        } else if (!this.registRecruitForm.career) {
          Swal.fire({ 
            icon: 'warning', // Alert 타입 
            title: '신입/경력을 선택해주세요.', // Alert 제목 
            text: '필수 항목을 모두 입력해야 등록이 가능합니다.', // Alert 내용 
          });
          // alert('신입/경력을 선택해주세요.');
        } else if (!this.registRecruitForm.descriptionURL) {
          Swal.fire({ 
            icon: 'warning', // Alert 타입 
            title: '기업소개 url을 입력해주세요.', // Alert 제목 
            text: '필수 항목을 모두 입력해야 등록이 가능합니다.', // Alert 내용 
          });
          // alert('기업소개 url을 입력해주세요.');
        } else if (!this.registRecruitForm.recruitURL) {
          Swal.fire({ 
            icon: 'warning', // Alert 타입 
            title: '채용공고 url을 입력해주세요.', // Alert 제목 
            text: '필수 항목을 모두 입력해야 등록이 가능합니다.', // Alert 내용 
          });
          // alert('채용공고 url을 입력해주세요.');
        }
      } else {
        Http.post('/recruit/upload', this.registRecruitForm)
          .then(() => {
            Swal.fire({ 
              icon: 'success', // Alert 타입 
              title: '채용 등록이 완료되었습니다.', // Alert 제목 
              text: '설명회도 참여해주세요! :)', // Alert 내용 
            });
            // alert('채용 공고 등록이 완료되었습니다.');
            this.$router.push({ name: 'wanted' });
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    dateCalendarClose() {
      if (this.recruitDate.length === 2) {
        this.dateCalendar = false;
      }
    },
    refreshAll() {
      this.$router.go()
    },
    cancel () {
      Swal.fire({ 
          icon: 'question', // Alert 타입 
          title: '등록을 취소하시겠습니까?', // Alert 제목
          text: '작성된 내용은 저장되지 않습니다.', 
          showCancelButton: true,
          showConfirmButton: true,
          confirmButtonColor: '#439474',
          confirmButtonText: `예`,
          cancelButtonText: `아니오`,
      })
      .then((res) => {
        console.log(res)
          if(res.isConfirmed) {
              router.push({ name: 'wanted' });
          }
      })
    },
  },
};
</script>

<style></style>
