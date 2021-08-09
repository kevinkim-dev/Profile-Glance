<template>
	<div class="d-flex">
		<div class="d-flex flex-column justify-content-around">
      <div class="infobox">이름: {{userInfo.userName}}</div>
      <div class="infobox">생년월일: {{userInfo.birth}}</div>
      <div class="infobox">좋아요 갯수: {{userInfo.countLike}}</div>
      <div class="infobox">포토폴리오1: {{userInfo.portfolio1}}</div>
		</div>
    <div class="d-flex flex-column justify-content-around">
      <div class="infobox">이메일: {{userInfo.userEmail}}</div>
      <div class="infobox">전공: {{majors}}</div>
      <div class="infobox">내 영상 갯수: {{userInfo.countVideo}}</div>
      <div class="infobox">포토폴리오2: {{userInfo.portfolio2}}</div>
		</div>
	</div>
</template>

<script>
export default {
  data() {
    return {

    }
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
  }
}
</script>

<style>
.infobox {
  width: 400px;
}
</style>