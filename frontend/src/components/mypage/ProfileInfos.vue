<template>
  <div class="profile-infobox">
    <div class="d-flex flex-column justify-content-around">
      <div class="infobox">좋아요 갯수: 20</div>
      <div class="infobox">내 영상 갯수: 4</div>
      <div class="infobox">전공: 실용무용</div>
    </div>
    <div class="d-flex flex-column justify-content-around">
      <div class="infobox">이름: 현승희</div>
      <div class="infobox">이메일: seunghee@naver.com</div>
      <div class="infobox">취미: 맛집탐방</div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.profile-infobox {
  display: flex;
  background-color: #eee;
  padding: 30px;
  height: 150px;
  margin-bottom: 10px;
}
.infobox {
  width: 400px;
}
</style>