<template>
  <div>
    <h1 class="text-center m-text5">Seunghee's Profile</h1>
    <div class="profile m-t-50">
      <div class="profile-left-box m-r-100">
        <ProfileImage />
        <ProfileMenus v-if="userType" />
      </div>
      <div class="profile-right-box">
        <ProfileInfos />
        <ProfileInterviews />
      </div>
    </div><hr class="m-t-50">
    <FeaturedProductList />
  </div>
</template>

<script>
import ProfileImage from '@/components/mypage/ProfileImage.vue';
import ProfileInfos from '@/components/mypage/ProfileInfos.vue';
import ProfileInterviews from '@/components/mypage/ProfileInterviews.vue';
import ProfileMenus from '@/components/mypage/ProfileMenus.vue';
import ProfileVideos from '@/components/mypage/ProfileVideos.vue';
import FeaturedProductList from '@/components/lookatme/FeaturedProductList.vue';

export default {
  name: 'profile',
  components: {
    ProfileImage,
    ProfileInfos,
    ProfileInterviews,
    ProfileMenus,
    ProfileVideos,
    FeaturedProductList
  },
  computed: {
    userType: function() {
      return this.$store.state.userType
    }
  }
}
</script>

<style>
.profile {
  display: flex ;
  justify-content: center;
}

.profile-left-box {
  height: 400px;
  width: 400px;
}

.profile-right-box {
  width: 800px;
  height: 400px;
}

</style>