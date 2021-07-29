<template>
  <div class="profile-menu-box d-flex flex-column justify-content-center">
    <div class="d-flex justify-content-around pt-10">
        <v-btn class="mb-5 edit-button"
              color="white"
              text
              @click="clickLogin"
            >
              회원정보 수정
        </v-btn>
        <v-btn class="mb-5 delete-button"
              color="white"
              text
              @click="clickLogin"
              v-if="isMe"
            >
              회원정보 탈퇴
        </v-btn>
        <v-btn class="mb-5 interview-button"
              color="white"
              text
              @click="clickLogin"
              v-else
            >
              면접 신청
        </v-btn>
    </div>
  </div>
</template>

<script>
export default {
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

.edit-button {
    background: blue;
    width: 130px;
}

.delete-button {
    background: crimson;
    width: 130px;
}

.interview-button {
    background: green;
    width: 130px;
}

</style>