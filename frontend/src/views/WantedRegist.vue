<template>
  <div>
    <v-form>
      <v-container>
        <v-row class="justify-center">
          <v-text class="h1">채용공고 등록</v-text>
          <hr>
          <v-col cols="12">
            <v-select
              label="직무"
              :items="jobs"
              v-model="registRecruitForm.job">
            </v-select>
            <v-text-field
              label="모집분야"
              v-model="registRecruitForm.jobDetail">
            </v-text-field>
            <v-radio-group
              row
              v-model="registRecruitForm.career"
            >
              <v-radio
                label="신입"
                value="신입"
              ></v-radio>
              <v-radio
                label="경력"
                value="경력"

              ></v-radio>
            </v-radio-group>
            <v-text-field
              label="회사소개 url"
              v-model="registRecruitForm.descriptionURL">
            </v-text-field>
            <v-text-field
              label="채용공고 url"
              v-model="registRecruitForm.recruitURL">
            </v-text-field>
            <v-row>
              <v-col cols="6">
                <v-btn block text x-large class="primary-color text-white rounded-0" @click="registRecruit">
                  등록
                </v-btn>
              </v-col>
              <v-col cols="6">
                <v-btn block text x-large class="secondary-color text-white rounded-0">
                  취소
                </v-btn>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
  </div>
</template>

<script>
export default {
  name: 'WantedRegist',
  data: function () {
    return {
      valid: true,
      jobs: ['IT', '마케팅', '영업', '경영지원', '디자인', '서비스', 
            '전문직', '의류', '생산제조', '건설', '유통무역', '미디어',
            '교육', '특수계층/공공', '연구직' ],
      registRecruitForm: {
        career: '',
        descriptionURL: '',
        job: '',
        jobDetail: '',
        presentationDate: '',
        recuritDate: '',
        recruitURL: '',
      },
    }
  },
  methods: {
    registRecruit () {
      if (!this.valid) {
        alert('유효성검사 x')
      } else {
      }
    }
  }
}
</script>

<style>

</style>