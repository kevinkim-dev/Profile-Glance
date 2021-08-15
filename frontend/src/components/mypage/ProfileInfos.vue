<template>
  <div>
    <div class="profile-infobox elevation-2" v-if="profileType!='company'">
      <AdminInfo v-if="profileType=='admin'" 
        @clickCompanySignUp="$emit('clickCompanySignUp')"
      />
      <UserInfo v-else-if="profileType=='user'" :userLike="userLike" />
    </div>
    <CompanyLikeUsers v-if="profileType=='company'" />
  </div>
</template>

<script>
import AdminInfo from "./profileinfo/AdminInfo.vue"
import CompanyLikeUsers from "./profileinfo/CompanyLikeUsers.vue"
import UserInfo from "./profileinfo/UserInfo.vue"

export default {
  components: {
    AdminInfo,
    CompanyLikeUsers,
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