<template>
    <div class="profile-image-box">
        <!-- <img class="profile-image" src="images/mypage/seunghee.jpg" alt="logo.png"> -->
        <!-- <img class="profile-image" src="images/mypage/samsung.png" alt="logo.png"> -->
        <!-- <img class="profile-image" src="images/mypage/admin.png" alt="logo.png"> -->
        <img class="profile-image" src="images/mypage/rabbit.jpg" alt="logo.png">
    </div>
</template>

<script>
export default {

}
</script>

<style>
.profile-image-box {
    position: relative;
    height: 300px;
    width: 300px;
    left: 80px;
    border-radius: 70%;
    overflow: hidden;
}

.profile-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
}
</style>