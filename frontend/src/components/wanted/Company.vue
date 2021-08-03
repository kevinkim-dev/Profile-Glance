<template class="col-sm-12 col-2">
  <div class="company d-flex align-center m-2 px-3 py-2" style="border: solid 3px #C0DDD1;">
    <!-- <p>{{ company }}</p> -->
    <div class="companyLogo d-flex align-center justify-center">
      <img class="rounded" :src="company.companyImg" :alt="company.companyName">
    </div>
    <div class="companyContent ms-5">
      <div class="d-flex align-center justify-space-between">
        <h4>{{ company.companyName }}</h4>
        <v-btn text rounded class="third-color text-white">live-on</v-btn>
      </div>
      <br>
      <h5>{{ company.career }}</h5>
      <h5>{{ company.job }}</h5>
      <h5>{{ company.jobDetail }}</h5>
      <div class="d-flex justify-end">
        <!-- <v-btn text class="primary-color text-white">회사소개</v-btn> -->
        <!-- {{ company.descriptionURL }} -->
        <a :href="company.descriptionURL" target="_blank" class="mx-2 subtitle-1 font-weight-bold" style="color: #439474;">회사소개</a>
        <a :href="company.recruitURL" target="_blank" class="mx-2 subtitle-1 font-weight-bold" style="color: #439474;">채용공고</a>
        <!-- <v-btn>채용공고</v-btn> -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Company',
  props: ['company'],

}
</script>

<style>
h5 {
  margin-top: 5px;
  margin-bottom: 5px;
}

img {
  background: white;
  width: 160px;
  height: 160px;
  object-fit: cover;
}

.company {
  width: 600px;
}

.companyLogo {
  width: 200px;
  height: 200px;
}

.companyContent {
  width: 400px;
}

.primary-color {
  color: #439474;
}

.secondary-color {
  color: #7D7D7D;
}

.third-color {
  background: #FF5E5E;
}
</style>