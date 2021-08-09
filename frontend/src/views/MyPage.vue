<template>
  <div>
    <h1 class="text-center">{{this.profileId}}</h1>
    <div class="profile m-t-50  ">
      <div class="profile-left-box m-r-100">
        <ProfileImage :isMyProfile="isMyProfile" />
        <ProfileMenus v-if="isMenuNeed" />
        <ProfileMyMenus v-if="isMyProfile && userType == 'user'" @clickEditButton="openEditModal" />
      </div>
      <div class="profile-right-box">
        <ProfileInfoButtons v-if="isMyProfile && userType != 'admin'" @clickInfo="clickInfo" @clickInterviews="clickInterviews" />
        <ProfileInterviews v-if="showInterview" />
        <ProfileInfos v-else />
      </div>
    </div><hr class="m-t-50">
    <FeaturedProductList v-if="profileType=='user'" />
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
      profileId: String,
      profileType: String,
      showInterview: false,
      isEditOpen: false,
      isCompanySignUpOpen: false,
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
    Edit,
  },
  computed: {
    isMenuNeed: function() {
      return localStorage.getItem('login_type') == 'company' && this.profileType == 'user'
    },
    isMyProfile: function() {
      const myType = localStorage.getItem('login_type')
      return (myType == this.profileType) && (localStorage.getItem('id') == this.profileId)
    },
    userType: function() {
      return localStorage.getItem('login_type')
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
  mounted() {
    const info = {
      'profileType': this.$route.params.loginType,
      'id': this.$route.params.id
    }
    if (info.profileType == 'user') {
      this.profileType = 'user'
      this.profileId = info.id
    } else if (info.profileType == 'company') {
      this.profileType = 'company'
      this.profileId = info.id
    } else if (info.profileType == 'admin') {
      this.profileType = 'admin'
      this.profileId = '관리자'      
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
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 400px;
  width: 400px;
}

.profile-right-box {
  display: flex;
  flex-direction: column;
  width: 800px;
  height: 400px;
}

</style>