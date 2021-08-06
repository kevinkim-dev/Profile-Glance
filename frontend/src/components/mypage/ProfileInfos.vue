<template>
  <div class="profile-infobox">
    <AdminInfo v-if="profileType==0" 
      @clickCompanySignUp="$emit('clickCompanySignUp')"
    />
    <UserInfo v-else-if="profileType==1" />
    <CompanyInfo v-else-if="profileType==2" />
  </div>
</template>

<script>
import AdminInfo from "./profileinfo/AdminInfo.vue"
import CompanyInfo from "./profileinfo/CompanyInfo.vue"
import UserInfo from "./profileinfo/UserInfo.vue"

export default {
  components: {
    AdminInfo,
    CompanyInfo,
    UserInfo,
  },
  computed: {
    profileType() {
      return this.$store.state.mypage.profileType
    }
  }
}
</script>

<style>
.profile-infobox {
  display: flex;
  background-color: #eee;
  padding: 30px;
  height: 100%;
  margin-bottom: 10px;
  font-size: 20px;
}
</style>