<template>
  <div class="d-flex flex-column justify-content-around m-l-110 m-t-30">
    <div class="infobox m-b-10">이메일: {{companyInfo.companyEmail}}</div>
    <div class="infobox">전화번호: {{companyInfo.companyPhone}}</div>
  </div>
</template>

<script>
export default {
  data() {
    return {

    }
  },
  computed: {
    companyInfo() {
      return this.$store.state.mypage.companyData
    }
  }
}
</script>

<style>
.infobox {
}
</style>