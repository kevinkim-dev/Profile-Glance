<template>
  <div class="d-flex flex-column justify-content-around m-t-15 m-b-15 text-start p-l-45">
    <div class="m-b-10"><i class="far fa-envelope m-r-5"></i> {{companyInfo.companyEmail}}</div>
    <div class=""><i class="fas fa-phone-alt m-r-5"></i> {{companyInfo.companyPhone}}</div>
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