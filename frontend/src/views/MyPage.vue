<template>
  <div>
    <h1 class="text-center">{{this.userName}}</h1>
    <div class="profile m-t-50">
      <div class="profile-left-box m-r-100">
        <ProfileImage :isMyProfile="isMyProfile" />
        <ProfileMenus v-if="isMenuNeed" />
        <ProfileMyMenus v-if="isMyProfile" @clickEditButton="openEditModal" />
      </div>
      <div class="profile-right-box">
        <ProfileInfoButtons v-if="isMyProfile" @clickInfo="clickInfo" @clickInterviews="clickInterviews" />
        <ProfileInterviews v-if="showInterview" />
        <ProfileInfos v-else />
      </div>
    </div><hr class="m-t-50">
    <FeaturedProductList />
    <v-dialog
      v-model="isEditOpen"
      max-width="650px"
    > <Edit @closeEditModal="closeEditModal" />
    </v-dialog>
  </div>
</template>

<script>
import ProfileImage from '@/components/mypage/ProfileImage.vue';
import ProfileInfos from '@/components/mypage/ProfileInfos.vue';
import ProfileInfoButtons from '@/components/mypage/ProfileInfoButtons.vue';
import ProfileInterviews from '@/components/mypage/ProfileInterviews.vue';
import ProfileMenus from '@/components/mypage/ProfileMenus.vue';
import ProfileMyMenus from '@/components/mypage/ProfileMyMenus.vue';
import ProfileVideos from '@/components/mypage/ProfileVideos.vue';
import FeaturedProductList from '@/components/lookatme/FeaturedProductList.vue';
import Edit from '@/components/mypage/Edit.vue'


export default {
  name: 'profile',
  data() {
    return {
      isEditOpen: false,
      userName: String,
      showInterview: false,
    }
  },
  components: {
    ProfileImage,
    ProfileInfos,
    ProfileInfoButtons,
    ProfileInterviews,
    ProfileMenus,
    ProfileMyMenus,
    ProfileVideos,
    FeaturedProductList,
    Edit
  },
  computed: {
    isMenuNeed: function() {
      return this.$store.state.userType == 2 && this.$store.state.mypage.profileType == 1
    },
    isMyProfile: function() {
      return (this.$store.state.userType == this.$store.state.mypage.profileType) &&
      (this.$store.state.userId == this.$store.state.mypage.profileId)
    }
  },
  methods: {
    openEditModal: function() {
      this.isEditOpen = true
    },
    closeEditModal: function() {
      this.isEditOpen = false
    },
    clickInfo: function() {
      this.showInterview = false
    },
    clickInterviews: function() {
      this.showInterview = true
    }
  },
  created() {
    this.userName = this.$store.state.mypage.userInfo.userName
  }
}
</script>

<style>
.profile {
  display: flex ;
  justify-content: center;
}

.profile-left-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 400px;
  width: 400px;
}

.profile-right-box {
  width: 800px;
  height: 400px;
}

</style>