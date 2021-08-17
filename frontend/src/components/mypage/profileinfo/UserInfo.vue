<template>
	<v-row>
    <v-col cols="6" class="infobox-box">
      <span class="infobox">이름: {{userInfo.userName}}</span>
    </v-col>
    <v-col cols="6" class="infobox-box">
      <span class="infobox">이메일: {{userInfo.userEmail}}</span>
    </v-col>
    <v-col cols="6" class="infobox-box">
      <span class="infobox">생년월일: {{userInfo.birth}}</span>
    </v-col>
    <v-col cols="6" class="infobox-box">
      <span class="infobox">전공: {{majors}}</span>
    </v-col>
    <v-col cols="6" class="infobox-box">
      <span class="infobox">좋아요 갯수: {{this.userLike}}</span>
    </v-col>
    <v-col cols="6" class="infobox-box">
      <span class="infobox">내 영상 갯수: {{userInfo.countVideo}}</span>
    </v-col>
    <v-col cols="6" class="infobox-box">
      <span class="infobox">포트폴리오1: {{userInfo.portfolio1}}</span>
    </v-col>
    <v-col cols="6" class="infobox-box">
      <span class="infobox">포트폴리오2: {{userInfo.portfolio2}}</span>
    </v-col>
	</v-row>
</template>

<script>
export default {
  props: {
    userLike: Number
  },
  computed: {
    majors() {
      const major1 = this.userInfo.major1
      const major2 = this.userInfo.major2
      if (!major1 && !major2) {
        return '전공 없음'
      } else if (major1 && major2) {
        return major1 + ', ' + major2
      } else {
        return major1 + major2
      }
    },
    userInfo() {
      return this.$store.state.mypage.userData
    }
  },
  // mounted() {
  //   this.userLike = this.$store.state.mypage.userData.countLike
  // }
}
</script>

<style>
.infobox {
  background-color: #d5e8d6;
  border-radius: 50px;
  border: 1px solid #cbddcc;
  padding-top: 15px;
  padding-bottom: 15px;
  padding-left: 20px;
  padding-right: 20px;
}

.infobox-box {
  display: flex;
  align-items: center;
}
</style>