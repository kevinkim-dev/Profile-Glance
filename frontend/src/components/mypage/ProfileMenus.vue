<template>
  <div class="profile-menu-box d-flex flex-column justify-content-center">
    <div class="d-flex justify-content-around pt-10">
        <profile-blue-button />
        <profile-red-button />
        <profile-green-button />
        <!-- <v-btn class="edit-button"
              color="white"
              text
              @click="clickLogin"
            >
              회원정보 수정
        </v-btn>
        <v-btn class="delete-button"
              color="white"
              text
              @click="clickLogin"
              v-if="isMe"
            >
              회원정보 탈퇴
        </v-btn>
        <v-btn class="interview-button"
              color="white"
              text
              @click="clickLogin"
              v-else
            >
              면접 신청
        </v-btn> -->
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
            return this.$store.state.isMe
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