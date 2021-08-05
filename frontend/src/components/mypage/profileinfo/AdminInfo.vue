<template>
  <div>
    <v-btn class="pg-button me-1"
      color="white"
      text
      @click="clickCompanySignUp"
    >
      기업회원 추가
    </v-btn>
  </div>
</template>

<script>
export default {
  methods: {
    clickCompanySignUp() {
      this.$router.push('companysignup  ')
    }
  }
}
</script>

<style>
.pg-button {
    width: 130px;
    background: #439474;
}
</style>