<template>
  <div class="profile-image-whole">
    <div class="profile-image-box">
      <img class="profile-image" :src="getImg()" alt="logo.png" />
    </div>
    <div class="filebox imageEdit" v-if="isMyProfile && (this.userType == 'user')">
      <label for="ex_file">사진 편집</label>
      <input type="file" id="ex_file" @change="changeImage" ref="img" accept="img/*" multiple />
    </div>
  </div>
</template>

<script>
import http from '@/http.js';
import { mapGetters } from 'vuex'

export default {
  data() {
    return {
      userType: localStorage.getItem('login_type')
    };
  },
  props: {
    isMyProfile: true,
  },
  computed: {
    ...mapGetters([
        'fileURL',
      ])
  },
  methods: {
    changeImage: function(e) {
      const file = e.target.files[0];
      const userEmail = this.$store.state.mypage.userData.userEmail;
      const formData = new FormData();
      formData.append('userImg', file);
      formData.append('userEmail', userEmail);
      http.post('/user/uploadImg', formData, {
        headers: { 'Content-Type': 'multipart/form-data' },
      })
        .then(({ data }) => {
          console.log(data);
          this.imgSrc = URL.createObjectURL(e.target.files[0]);
          location.reload();
        })
        .catch((err) => console.log(err));
    },
    getImg() {
      const myType = localStorage.getItem('login_type')
      if (myType == 'user') {
        return (
          this.fileURL + 'ServerFiles/UserImg/' +
          this.$store.state.mypage.userData.userImg
        );
      } else if (myType == 'company') {
        return (
          this.fileURL + 'ServerFiles/CompanyLogo/' +
          this.$store.state.mypage.companyData.companyLogo
        );
      } else if (myType == 'admin') {
        return (
          this.fileURL + 'images/mypage/admin.png'
        )
      }
    },
  },
};
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
  position: relative;
  top: -20px;
  left: 270px;
}

.profile-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.filebox label {
  display: inline-block;
  padding: 0.5em 0.75em;
  color: black;
  font-size: inherit;
  line-height: normal;
  vertical-align: middle;
  background-color: #fdfdfd;
  cursor: pointer;
  border: 1px solid #ebebeb;
  border-radius: 0.25em;
}

.filebox input[type='file'] {
  /* 파일 필드 숨기기 */
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  border: 0;
}
</style>
