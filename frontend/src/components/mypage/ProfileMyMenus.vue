<template>
  <div class="profile-menu-box d-flex flex-column justify-content-center">
    <div class="d-flex justify-content-around pt-10">
        <v-btn class="blue-button"
            color="white"
            text
            @click="$emit('clickEditButton')"
        >
            회원정보 수정
        </v-btn>
        <v-btn class="red-button"
            color="white"
            text
            @click="clickDeleteButton"
        >
            회원 탈퇴
        </v-btn>
    </div>
  </div>
</template>

<script>
export default {
    methods: {
        clickDeleteButton() {
            this.$store.dispatch('requestDeleteUser', localStorage.getItem('user_email'))
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

.blue-button {
    background: blue;
    width: 130px;
}

.red-button {
    background: crimson;
    width: 130px;
}
</style>