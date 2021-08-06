<template class="col-sm-12 col-2">
  <div class="company d-flex align-center m-2 px-3 py-4" style="border: solid 3px #C0DDD1;">
    <div class="companyLogo d-flex align-center justify-center">
      <img class="rounded" :src="getImgPath()" :alt="company.companyName" />
    </div>
    <div class="companyContent ms-5">
      <div class="d-flex align-center justify-space-between">
        <h4>{{ company.companyName }}</h4>
        <v-btn text rounded class="third-color text-white">live-on</v-btn>
      </div>
      <br />
      <h5>{{ company.career }}</h5>
      <div class="d-flex justify-space-between">
        <h5>[{{ company.job }}] {{ company.jobDetail }}</h5>
        <v-chip label v-if="company.presentationDate">
          {{ company.presentationDate | moment('MM월 DD일 HH:mm') }} 설명회
        </v-chip>
      </div>
      <div class="d-flex justify-end mt-2">
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
    </div>
  </div>
</template>

<script>
export default {
  name: 'Company',
  props: ['company'],
  data: function() {
    return {};
  },
  methods: {
    getImgPath: function() {
      // return require("@/../public/ServerFiles/CompanyLogo/"+ this.company.companyImg )
      return 'http://profileglance.site/ServerFiles/CompanyLogo/' + this.company.companyImg;
    },
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
  width: 600px;
}

.companyLogo {
  width: 180px;
  height: 180px;
}

.companyContent {
  width: 400px;
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
