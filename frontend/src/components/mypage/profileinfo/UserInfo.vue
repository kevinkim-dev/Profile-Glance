<template>
	<div class="d-flex">
		<div class="d-flex flex-column justify-content-around">
      <div class="infobox">이름: {{this.userInfo.userName}}</div>
      <div class="infobox">생년월일: {{this.userInfo.userBirth}}</div>
      <div class="infobox">좋아요 갯수: {{this.userInfo.companyLike}}</div>
      <div class="infobox">포토폴리오1: {{this.userInfo.portfolio1}}</div>
		</div>
    <div class="d-flex flex-column justify-content-around">
      <div class="infobox">이메일: {{this.userInfo.userEmail}}</div>
      <div class="infobox">전공: {{majors}}</div>
      <div class="infobox">내 영상 갯수: {{this.userInfo.companyLike}}</div>
      <div class="infobox">포토폴리오2: {{this.userInfo.portfolio2}}</div>
		</div>
	</div>
</template>

<script>
export default {
  data() {
    return {
      userInfo: {
        userEmail: String,
        userName: String,
        userNickname: String,
        userBirth: String,
        userPhone: String,
        major1: String,
        major2: String,
        companyLike: Number,
        portfolio1: String,
        portfolio2: String,
      }
    }
  },
  created() {
    this.userInfo=this.$store.state.mypage.data.userData
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
    }
  }
}
</script>

<style>
.infobox {
  width: 400px;
}
</style>