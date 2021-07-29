<template>
  <div class="profile-menu-box d-flex flex-column justify-content-center">
    <div class="d-flex justify-content-around pt-10">
        <profile-blue-button :isMe="isMe" />
        <profile-red-button :isMe="isMe" />
        <profile-green-button :isInterviewAvailable="isInterviewAvailable" />
    </div>
  </div>
</template>

<script>
import ProfileRedButton from './buttons/ProfileRedButton.vue'
import ProfileGreenButton from './buttons/ProfileGreenButton.vue'
import ProfileBlueButton from './buttons/ProfileBlueButton.vue'

export default {
    components: {
        ProfileRedButton,
        ProfileGreenButton,
        ProfileBlueButton
    },
    computed: {
        isMe: function() {
            return (this.$store.state.userType == this.$store.state.mypage.profileType) &&
            (this.$store.state.userId == this.$store.state.mypage.profileId)
        },
        isInterviewAvailable: function() {
            return (this.$store.state.userType == 2 && this.$store.state.mypage.profileType == 1)
        }
    }
}
</script>

<style>
.profile-menu-box {
    margin-left: 80px;
    width: 300px;
    height: 100px;  
}
</style>