<template>
  <div class="d-flex flex-column justify-content-around m-t-15 m-b-15 text-center">
    <div class="m-b-10">이메일: {{companyInfo.companyEmail}}</div>
    <div class="">전화번호: {{companyInfo.companyPhone}}</div>
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

</style>