<template>
  <v-col cols="6" align="center">
  <v-card
    elevation="1"
    outlined
    class="m-2 company-card"
  >
    <div class="company d-flex flex-column px-3 pt-4">
      <div class="d-flex">
        <div class="companyLogo-box d-flex align-center justify-center">
          <img class="rounded companyLogo" :src="getImgPath()" :alt="company.companyName" />
        </div>
        <div class="companyContent ms-5">
          <div class="d-flex align-center justify-space-between">
            <h4>{{ company.companyName }}</h4>
            <v-btn
              text rounded class="third-color text-white"
              v-if="company.sessionId"
              @click="enterPresentation(company.sessionId, company.companyName)">live-on</v-btn>
          </div>
          <br />
          <h6 class="mb-2" style="text-align:left">{{ company.career }}</h6>
          <h6 style="text-align:left">[{{ company.job }}] {{ company.jobDetail }}</h6>
        </div>
      </div>
      <div class="d-flex justify-content-between align-end mt-5">
        <div>
          <a
            :href="company.descriptionURL"
            target="_blank"
            class="mx-3 subtitle-1 font-weight-bold"
            style="color: #439474;"
            >회사소개</a
          >
          <a
            :href="company.recruitURL"
            target="_blank"
            class="mx-2 subtitle-1 font-weight-bold"
            style="color: #439474;"
            >채용공고</a
          >
        </div>
        <v-chip label v-if="company.presentationDate">
          {{ company.presentationDate | moment('MM월 DD일 HH:mm') }} 설명회
        </v-chip>
      </div>
    </div>
  </v-card>
  </v-col>
</template>

<script>
import router from '@/router';
import { mapGetters } from 'vuex'
export default {
  name: 'Company',
  props: ['company'],
  computed: {
    ...mapGetters([
      'fileURL'
    ])
  },
  methods: {
    getImgPath: function() {
      return this.fileURL + 'ServerFiles/CompanyLogo/' + this.company.companyImg;
    },
    enterPresentation: function(sessionId, companyName) {
      Swal.fire({ 
          icon: 'question', // Alert 타입 
          title: '설명회에 입장하시겠습니까?', // Alert 제목
          showCancelButton: true,
          showConfirmButton: true,
          confirmButtonColor: '#439474',
          confirmButtonText: `예`,
          cancelButtonText: `아니오`,
      })
      .then((res) => {
          if(res.isConfirmed) {
              router.push({ name: 'userpresentation', params: { sessionid: sessionId, companyname: companyName }})
          }
      })
    }
  },
};
</script>

<style>
h5 {
  margin-top: 5px;
  margin-bottom: 5px;
}

img {
  background: white;
  width: 150px;
  height: 150px;
  object-fit: cover;
}

.company {
  width: 100%;
}

.company-card {
  width: 530px;
  height: 180px;
  position: relative;
}

.companyLogo-box {
  width: 100px;
  height: 100px;
}

.companyLogo {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.companyContent {
  width: 200px;
}

.primary-color {
  color: #439474;
}

.secondary-color {
  color: #7d7d7d;
}

.third-color {
  background: #ff5e5e;
}
</style>
