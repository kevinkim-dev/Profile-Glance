<template>
  <div id="real-background">
    <v-img id ="background" src="images/icons/pg_thunder.png"></v-img>
    <div id="home-top"></div>
    <v-row justify="end" id="login-btn">
      <v-dialog v-model="isLoginViewd" max-width="600px">
        <template v-slot:activator="{ on, attrs }">
          <v-btn x-large color="#439474" v-bind="attrs" dark v-on="on">
            join
          </v-btn>
        </template>
        <login @close="closeLogin" @signup="openSignUp" />
      </v-dialog>
      <v-dialog
        v-model="isSignUpViewd"
        max-width="650px"
      > <sign-up @close="closeSignUp" @login="openLogin" />
      </v-dialog>
    </v-row>
    <v-container class="home-container-top">
      <img class="home-logo" src="/images/icons/logo_and_letter.png" alt="IMG-LOGO">
      <div id="down-btn-box">
        <v-img id="down-btn"
          class="move-button"
          contain
          src="/images/icons/down.png"
        ></v-img>
      </div>
    </v-container>
    <div id="home-bottom"></div>
    <v-container class="home-container container1">
      <div class="home-main-content row">
        <div class="home-main-content-left home-profileglance">
          <div class="home-main-content-box">
            <h1 style="font-weight: bold;">Profile Glance</h1> 
            <h3 class="mt-5 mb-5">더 자유롭게 자신을 표현하세요</h3>
            <p>구인 구직 플랫폼의 혁신, Profile Glance</p>
            <p>취업준비생에게는 영상을 통한 역량 표현의 기회를,</p>
            <p>기업에게는 적합한 인재를 선발할 기회를</p>
            <p>제공하는 온라인 구인 구직 플랫폼입니다.</p>
            <p>자유로운 자신 표현과 다양한 공고를 통해 당신의 채용을 완성하세요.</p>
          </div>
          <div class="home-half-image-box">
            <img src="/images/icons/home_lookatme1.png" alt="" class="profile-image">
          </div>
        </div>
        <div class="home-main-content-right home-lookatme">
          <div>

          </div>
          <div class="home-half-image-box">
            <img src="/images/icons/home_lookatme2.png" alt="" class="profile-image">
          </div>
          <div class="home-main-content-box m-t-20">
            <h3 class="mt-5 mb-5" style="font-weight: bold;">Look At Me</h3>
            <p>Profile Glance는 글이 아닌 영상을 통해 자신을 소개하고 표현할 수 있습니다.</p>
            <p>다양한 분야의 사람들의 영상을 보고, 자신만의 영상을 올릴 수 있습니다.</p>
            <p>기업은 마음에 드는 역량의 검증된 구직자를 고를 수 있습니다.</p>
          </div>
        </div>
      </div>
    </v-container>
    <v-container class="home-container container2">
      <div class="home-main-content row">
        <div class="home-main-content-left">
          <div class="home-main-content-box m-b-40">
            <h3 class="m-t-130 mb-5" style="font-weight: bold;">Wanted</h3>
            <p>다양한 기업과 여러 분야의 채용을 확인할 수 있습니다.</p>
            <p>원하는 기업의 채용 설명회에 참여할 수 있고 지원할 수 있습니다.</p>
            <p>원하는 기업에 지원할 수 있고 채용 설명회에도 참여할 수 있습니다.</p>
          </div>
          <div class="home-half-image-box m-t-177 m-b-80" style="border: 1px rgb(180, 180, 180) solid; height: 290px">
            <img src="/images/icons/home_presentation.png" alt="" class="profile-image">
          </div>
        </div>
        <div class="home-main-content-right">
          <div class="home-half-image-box m-b-80">
            <img src="/images/icons/home_wanted.png" alt="" class="profile-image">
          </div>
          <div class="home-half-image-box m-t-50">
            <img src="/images/icons/home_interview.png" alt="" class="profile-image">
          </div>
        </div>
        <div class="home-main-content-last-box m-b-40">
          <h3 class="mt-5 mb-5" style="font-weight: bold;">채용 설명회 & 채용 면접</h3>
          <p>다양한 기업들의 채용설명회를 라이브 스트리밍으로 들을 수 있고 채팅을 통해 실시간으로 질의응답이 가능합니다.</p>
          <p>뿐만 아니라 각 기업들과 면접도 볼 수 있는 all-in-one 플랫폼입니다.</p>
        </div>
      </div>
    </v-container>
    <div id="home-real-bottom"></div>
    <div class="home-container">
      <div class="profile-card-box">
        <h1 class="m-l-100">Team</h1>
        <div class="profile-card-row">
          <div class="profile-card elevation-2">
            <div class="profile-image-box1">
              <img src="/images/icons/logo.png" alt="our_profile.png" class="profile-image">
            </div>
            <div class="profile-info-box">
              <h4 class="m-b-30">이름: 권순주</h4>
              <h6>파트: 백앤드</h6>
            </div>
          </div>
          <div class="profile-card elevation-2">
            <div class="profile-image-box1">
              <img src="/images/icons/logo.png" alt="our_profile.png" class="profile-image">
            </div>
            <div class="profile-info-box">
              <h4 class="m-b-30">이름: 김동훈</h4>
              <h6>파트: 프론트앤드</h6>
            </div>
          </div>
          <div class="profile-card elevation-2">
            <div class="profile-image-box1">
              <img src="/images/icons/logo.png" alt="our_profile.png" class="profile-image">
            </div>
            <div class="profile-info-box">
              <h4 class="m-b-30">이름: 김승현</h4>
              <h6>파트: 프론트앤드</h6>
            </div>
          </div>
        </div>
        <div class="profile-card-row">
          <div class="profile-card elevation-2">
            <div class="profile-image-box1">
              <img src="/images/icons/logo.png" alt="our_profile.png" class="profile-image">
            </div>
            <div class="profile-info-box">
              <h4 class="m-b-30">이름: 민동엽</h4>
              <h6>파트: 백앤드</h6>
            </div>
          </div>          
          <div class="profile-card elevation-2">
            <div class="profile-image-box1">
              <img src="/images/icons/logo.png" alt="our_profile.png" class="profile-image">
            </div>
            <div class="profile-info-box">
              <h4 class="m-b-30">이름: 심재우</h4>
              <h6>파트: 백앤드</h6>
            </div>
          </div>          
          <div class="profile-card elevation-2">
            <div class="profile-image-box1">
              <img src="/images/icons/logo.png" alt="our_profile.png" class="profile-image">
            </div>
            <div class="profile-info-box">
              <h4 class="m-b-30">이름: 정은교</h4>
              <h6>파트: 프론트앤드</h6>
            </div>
          </div>
        </div>
      </div>
    </div>
    <v-container class="home-container-bottom">
      <div id="up-btn-box">
        <v-img id="up-btn"
          class="move-button"
          contain
          src="images/icons/down.png"
          style="transform:rotate(180deg)"
        ></v-img>
      </div>  
    </v-container>
    <div class="navi elevation-3">
      <div class="text-center stick stick1" id="sticky-menu1">Top</div>
      <div class="text-center stick" id="sticky-menu2">PG Info</div>
      <div class="text-center stick stick1" id="sticky-menu3">Team</div>
    </div>
  </div>
</template>
<style>
#real-background p {
  color: #505050;
}

.navi {
  position: sticky;
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100px;
  height: 120px;
  left: 3vw;
  bottom: 15vh;
  border: 1px solid black;
  border-radius: 5px;
  background: #eee;
}

.stick {
  font-weight: bold;
  font-size: 14px;
  padding-top: 5px;
  padding-bottom: 5px;
  background: white;
}

.stick:hover {
  cursor: pointer;
}

.stick1 {
  border-top: 1px solid gray;
  border-bottom: 1px solid gray;
}

.profile-card-box {
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  width: 100%;
  height: 100%;
}

.profile-card-row {
  display: flex;
  justify-content: space-evenly;
  position: relative;
  width: 100%;
  height: 30%;
}

.profile-card {
  height: 100%;
  width: 30%;
  border: outset;
  border-radius: 10px;
  display: flex;
  justify-content: flex-start;
  background: white;
}


.profile-image-box1 {
  width: 100px;
  height: 100px;
  margin-left: 40px;
  margin-top: 40px;
  border-radius: 70%;
  overflow: hidden;
  border: 1px solid gray;
}

.profile-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.profile-info-box {
  width: auto;
  padding-top: 60px;
  padding-left: 60px;
}

.home-main-content {
  display: flex;
  width: 100%;
  padding-left: 50px;
  padding-right: 50px;
}

.home-main-content-left {
  text-align: center;
  width: 50%;
  padding: 10px;
}

.home-main-content-right {
  text-align: center;
  width: 50%;
  padding: 10px;
}

.home-main-content-last-box {
  text-align: center;
  width: 100%;
}

.home-profileglance {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.home-lookatme {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
}

.home-half-image-box {
  width: 100%;
}

#real-background {
  background: linear-gradient( to bottom, rgb(255, 255, 255), #d3d3d3 );
}

#background{
  position: absolute;
  left: 10vw;
  width : 80vw;
  height: auto;
  opacity:0.8;
}

.desc {
  position: absolute;
  top: 100vh;
}

#login-btn {
  position: absolute;
  top: 10vh;
  left: 78vw;
  z-index: 1000;
}

.home-container-top {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
  height: 100vh;
}

.home-container {
  display: flex;
  flex-direction: column;
  /* align-items: center; */
  position: relative;
  height: 120vh;
}

.container1 {
  padding-top: 20vh;
  height: 120vh;
}

.container2 {
  padding-bottom: 20vh;
  height: 140vh;
}

.home-container-bottom {
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
  height: 40vh;
}

.home-logo {
  height: 200px;
  width: 900px;
  object-fit: cover;
  background: none;
  margin-left: 20px; 
  margin-bottom: 50px;
  
}

#down-btn-box {
  position: absolute;
  display: flex;
  justify-content: center;
  top: 70vh;
  width: 10vw;
}

#up-btn-box {
  position: absolute;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 10vw;
  top:10%;
}

#up-btn,
#down-btn:hover {
  cursor: pointer;
}

.move-button {
  animation: motion 0.6s linear 0s infinite alternate; 
}
        
@keyframes motion {
  0% {margin-top: 0px;}
  100% {margin-top: 12px;}
}

</style>
<script>
import Login from "@/components/home/Login.vue";
import SignUp from "@/components/home/SignUp.vue";
export default {
  components: {
      Login,
      SignUp,
  },
  data() {
      return {     
          isLoginViewd: false,
          isSignUpViewd: false,
          desc: "#desc",
          top: "#login-btn",
      }
  },
  mounted() {
    $(document).ready(function() {
      $("#down-btn").on("click", function(event) {
        var offset = $("#home-bottom").offset();
        $("html").animate({scrollTop:offset.top}, 1800);
      });
      $("#up-btn").on("click", function(event) {
        var offset = $("#home-top").offset();
        $("html").animate({scrollTop:offset.top}, 1500);
      });
      $("#sticky-menu1").on("click", function(event) {
        var offset = $("#home-top").offset();
        $("html").animate({scrollTop:offset.top}, 1500);
      });
      $("#sticky-menu2").on("click", function(event) {
        var offset = $("#home-bottom").offset();
        $("html").animate({scrollTop:offset.top}, 1500);
      });
      $("#sticky-menu3").on("click", function(event) {
        var offset = $("#home-real-bottom").offset();
        $("html").animate({scrollTop:offset.top}, 1500);
      });

    })
  },
  methods: {
    openLogin() {
      this.isLoginViewd = true
      this.isSignUpViewd = false
    },
    closeLogin() {
      this.isLoginViewd = false
    },
    openSignUp() {
      this.isSignUpViewd = true
      this.isLoginViewd = false
    },
    closeSignUp() {
      this.isSignUpViewd = false
    },
  }
}
</script>
