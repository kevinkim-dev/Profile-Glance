<template>
  <div>
    <h1 class="text-center">{{profileId}}</h1>
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
    <ProfileLookatme v-if="profileType=='user'" />
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
import ProfileLookatme from '@/components/mypage/ProfileLookatme.vue';
import Edit from '@/components/mypage/Edit.vue'


export default {
  name: 'profile',
  data() {
    return {
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
    ProfileLookatme,
    Edit,
  },
  computed: {
    isMenuNeed: function() {
      return localStorage.getItem('login_type') == 'company' && this.$route.params.loginType == 'user'
    },
    isMyProfile: function() {
      return (localStorage.getItem('login_type') == this.$route.params.loginType) && (localStorage.getItem('id') == this.$route.params.id)
    },
    userType: function() {
      return localStorage.getItem('login_type')
    },
    profileType() {
      // return this.$store.state.mypage.profileType
      return this.$route.params.loginType
    },
    profileId() {
      return this.$route.params.id
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
    // if (info.profileType == 'user') {
    //     this.profileType = 'user'
    //   this.profileId = info.id
    // } else if (info.profileType == 'company') {
    //     this.profileType = 'company'
    //   this.profileId = info.id
    // } else if (info.profileType == 'admin') {
    //     this.profileType = 'admin'
    //   this.profileId = '관리자'      
    // }
    const info = {
      'profileType': this.$route.params.loginType,
      'id': this.$route.params.id
    }
    this.$store.dispatch('mypage/getUserData', info)
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