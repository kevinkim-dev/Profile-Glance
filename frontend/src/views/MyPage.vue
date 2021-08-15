<template>
  <div>
    <h1 class="text-center m-t-30">{{profileId}}</h1>
    <div class="profile m-t-30  ">
      <div class="profile-left-box m-r-100">
        <ProfileImage :isMyProfile="isMyProfile" />
        <ProfileMenus v-if="isMenuNeed" @openInterviewModal="openInterviewModal" @unliked="unliked" @liked="liked" />
        <ProfileMyMenus v-if="isMyProfile && userType == 'user'" @clickEditButton="openEditModal" />
        <CompanyInfo v-if="userType == 'company'" />
      </div>
      <div class="profile-right-box">
        <ProfileInfoButtons v-if="isMyProfile && userType != 'admin'" @clickInfo="clickInfo" @clickInterviews="clickInterviews" @clickWanteds="clickWanteds"/>
        <ProfileInterviews v-if="infoCategory == 'interview'" />
        <ProfileInfos v-if="infoCategory == 'info'" :userLike="this.userLike" />
        <ProfileWanteds v-if="infoCategory == 'wanted'" />
      </div>
    </div><hr class="m-t-50">
    <ProfileLookatme v-if="profileType=='user'" :profileId="profileId" />
    <v-dialog
      v-model="isEditOpen"
      max-width="650px"
    > <EditModal @closeEditModal="closeEditModal" class="mypageModal" />
    </v-dialog>
    <v-dialog
      v-model="isInterviewModalOpen"
      max-width="650px"
    > <InterviewModal @closeInterviewModal="closeInterviewModal" class="mypageModal" />
    </v-dialog>
  </div>
</template>

<script>
import ProfileImage from '@/components/mypage/ProfileImage.vue';
import ProfileInfos from '@/components/mypage/ProfileInfos.vue';
import ProfileInfoButtons from '@/components/mypage/ProfileInfoButtons.vue';
import ProfileInterviews from '@/components/mypage/interview/ProfileInterviews.vue';
import ProfileWanteds from '@/components/mypage/wanted/ProfileWanteds.vue';
import CompanyInfo from '@/components/mypage/profileinfo/CompanyInfo.vue';
import ProfileMenus from '@/components/mypage/ProfileMenus.vue';
import ProfileMyMenus from '@/components/mypage/ProfileMyMenus.vue';
import ProfileVideos from '@/components/mypage/ProfileVideos.vue';
import ProfileLookatme from '@/components/mypage/ProfileLookatme.vue';
import EditModal from '@/components/mypage/EditModal.vue'
import InterviewModal from '@/components/mypage/InterviewModal.vue'


export default {
  name: 'profile',
  data() {
    return {
      infoCategory: 'info',
      isEditOpen: false,
      isCompanySignUpOpen: false,
      isInterviewModalOpen: false,
      userLike: Number,
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
    EditModal,
    InterviewModal,
    ProfileWanteds,
    CompanyInfo
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
    openInterviewModal: function() {
      this.isInterviewModalOpen = true
    },
    closeInterviewModal: function() {
      this.isInterviewModalOpen = false
    },
    clickInfo: function() {
      this.infoCategory = 'info'
    },
    clickInterviews: function() {
      this.infoCategory = 'interview'
    },
    clickWanteds: function() {
      this.infoCategory = 'wanted'
    },
    unliked: function() {
      this.userLike -= 1
    },
    liked: function() {
      this.userLike += 1
    },

  },
  mounted() {
    const info = {
      'profileType': this.$route.params.loginType,
      'id': this.$route.params.id
    }
    this.$store.dispatch('mypage/getUserData', info)
    this.userLike = this.$store.state.mypage.userData.countLike
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

.mypageModal {
  z-index: 1100;
}
</style>