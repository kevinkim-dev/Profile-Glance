<template>
  <div>
    <div class="profile m-t-70">
      <div class="profile-left-box m-l-50 elevation-2">
        <ProfileImage :isMyProfile="isMyProfile" />
        <div class="text-center userNickname elevation-2">{{profileId}}</div>
        <ProfileMenus v-if="isMenuNeed" @openInterviewModal="openInterviewModal" @unliked="unliked" @liked="liked" />
        <ProfileMyMenus v-if="isMyProfile && userType == 'user'" @clickEditButton="openEditModal" />
        <CompanyInfo v-if="profileType == 'company'" />
      </div>
      <div class="profile-right-box d-flex">
        <div>
          <ProfileInterviews v-if="infoCategory == 'interview'" />
          <ProfileInfos v-if="infoCategory == 'info'" :userLike="this.userLike" />
          <ProfileWanteds v-if="infoCategory == 'wanted'" />
        </div>
        <ProfileInfoButtons v-if="isMyProfile && userType != 'admin'" @clickInfo="clickInfo" @clickInterviews="clickInterviews" @clickWanteds="clickWanteds"/>
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
import http from '@/http.js'

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
    if (info.profileType == 'user') {
      http.get('user/myinfo/nickname/' + info.id)
      .then((res) => {
        this.userLike = res.data.countLike
      })
    }
  }
}
</script>

<style>
.userNickname {
  font-size: 30px;
  padding: 4px;
  padding-left: 10px;
  padding-top: 8px;
  margin-top: 20px;
  margin-left: 10%;
  width: 80%;
  border-radius: 30px;
  border: outset;
  background: #439474;
  color: white;
  text-align: center;
}

.profile {
  display: flex ;
  justify-content: center;
}

.profile-left-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 400px;
  width: 300px;
  background-color: #EAF5F1;
  border-radius: 6px;
}

.profile-right-box {
  display: flex;
  width: 860px;
  height: 400px;
  margin-left: 70px;
}

.mypageModal {
  z-index: 1100;
}
</style>