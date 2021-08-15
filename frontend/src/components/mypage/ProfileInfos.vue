<template>
  <div class="profile-infobox elevation-2">
    <AdminInfo v-if="profileType=='admin'" 
      @clickCompanySignUp="$emit('clickCompanySignUp')"
    />
    <UserInfo v-else-if="profileType=='user'" :userLike="userLike" />
    <CompanyInfo v-else-if="profileType=='company'" />
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
  props: {
    userLike: Number
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
  background-color: #eee;
  display: flex;
  padding: 30px;
  height: 100%;
  font-size: 20px;
  border-radius: 4px;
}
</style>