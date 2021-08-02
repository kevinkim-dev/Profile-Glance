<template>
    <div class="profile-image-whole">
        <div class="profile-image-box">
            <img class="profile-image" src="images/mypage/rabbit.jpg" alt="logo.png">
        </div>
        <!-- <img class="profile-image" src="images/mypage/seunghee.jpg" alt="logo.png"> -->
        <!-- <img class="profile-image" src="images/mypage/samsung.png" alt="logo.png"> -->
        <!-- <img class="profile-image" src="images/mypage/admin.png" alt="logo.png"> -->
        <v-btn class="imageEdit" v-if="isMe ">사진 편집</v-btn>
    </div>
</template>

<script>
export default {
    computed: {
        isMe: function() {
            return (this.$store.state.userType == this.$store.state.mypage.profileType) &&
            (this.$store.state.userId == this.$store.state.mypage.profileId)
        },
    }
}
</script>

<style>
.profile-image-whole {
    height: 300px;
    width: 300px;
}

.profile-image-box {
    position: relative;
    height: 300px;
    width: 300px;
    left: 80px;
    border-radius: 70%;
    overflow: hidden;
}

.imageEdit {
    border: black solid 1px;
    position: relative;
    top: -20px;
    left: 270px;
}

.profile-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
}
</style>