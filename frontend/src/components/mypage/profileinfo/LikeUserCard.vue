<template>
  <div class="d-flex justify-content-around align-items-center">
    <div class="like-image-box">
      <img :src="getImg(this.likeUser.userImg)" class="like-image" alt="profile_img">
    </div>
    <div class="m-l-10">{{ this.likeUser.userNickname }}</div>
    <v-btn class="interview-button elevation-1"
              color="white" text
              @click="clickProfile(likeUser.userNickname)"
            >
              프로필 보기
    </v-btn>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  props: {
    likeUser: Object
  },
  computed: {
    ...mapGetters([
      'fileURL',
    ]),
  },
  methods: {
    clickProfile(userNickname) {
      Swal.fire({ 
          icon: 'question', // Alert 타입 
          title: userNickname + '님의 프로필을 보시겠어요?', // Alert 제목 
          showCancelButton: true,
          confirmButtonText: `네`,
          cancelButtonText: `아니오`,
      })
      .then((res) => {
          if(res.isConfirmed) {
              this.$router.push('/mypage/user/' + userNickname)
          }
      })
    },
    getImg(userImg) {
      return (
          this.fileURL + 'ServerFiles/UserImg/' +
          userImg
      )
    },
  },
}
</script>

<style>
.interview-button {
    width: 100px;
    background: #439474;
    padding: 0px;
}

.like-image-box {
    height: 50px;
    width: 50px;
    border-radius: 70%;
    overflow: hidden;
}

.like-image {
    width: 100%;
    height: 100%;   
    object-fit: cover;
}
</style>