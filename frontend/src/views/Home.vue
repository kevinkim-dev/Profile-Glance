<template>
  <div>
    <div id="home-top"></div>
    <v-row justify="end" id="login-btn">
      <v-dialog
        v-model="isLoginViewd"
        max-width="600px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            color="#439474"
            dark
            v-bind="attrs"
            v-on="on"
          >
            구르러 가기
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
    <v-container class="home-container home-top-box">
      <v-row justify="center" id="logo" align="end">
          <v-img
            contain
            max-height="20vh"
            max-width="100vw"
            src="images/icons/pgpg.png"
          ></v-img>
      </v-row>
      <v-row justify="center" id="title">
          <v-img
            contain
            height="20vh"
            width="100vw"
            src="images/icons/pg.png"
          ></v-img>
      </v-row>
      <v-row justify="center" class="desc-btn" align="start">
        <button type="button" id="down-btn">
          <v-img
              contain
              max-height="20vh"
              max-width="100vw"
              src="images/icons/down.png"
            ></v-img>
        </button>
      </v-row>
    </v-container>
    <v-container class="home-container home-bottom-box">
      <div id="home-bottom"></div>
      <v-row>
        <v-row justify="center" class="desc-btn" id="up-btn">
          <button type="button">
            <v-img
                contain
                max-height="20vh"
                max-width="100vw"
                src="images/icons/down.png"
                style="transform:rotate(180deg)"
              ></v-img>
          </button>
        </v-row>
        <v-row justify="center">
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita et eum quaerat dolore pariatur laudantium inventore voluptatem minima consectetur amet corrupti fugit deserunt eos porro, dignissimos qui blanditiis quidem aliquid.
        </v-row>
        <v-row justify="center">
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita et eum quaerat dolore pariatur laudantium inventore voluptatem minima consectetur amet corrupti fugit deserunt eos porro, dignissimos qui blanditiis quidem aliquid.
        </v-row>
        <v-row justify="center">
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita et eum quaerat dolore pariatur laudantium inventore voluptatem minima consectetur amet corrupti fugit deserunt eos porro, dignissimos qui blanditiis quidem aliquid.
        </v-row>
        <v-row justify="center">
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita et eum quaerat dolore pariatur laudantium inventore voluptatem minima consectetur amet corrupti fugit deserunt eos porro, dignissimos qui blanditiis quidem aliquid.
        </v-row>
      </v-row>
    </v-container>
  </div>
</template>

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
      console.log("click down");
      var offset = $("#home-bottom").offset();
      console.log(offset);
      $("html").animate({scrollTop:offset.top}, 1800);
    });
    $("#up-btn").on("click", function(event) {
      console.log("click up");
      var offset = $("#home-top").offset();
      console.log(offset);
      $("html").animate({scrollTop:offset.top}, 1500);
    })
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

<style>
.desc {
  position: absolute;
  top: 100vh;

}

#login-btn {
  position: sticky;
  top: 50px;
  margin-right: 50px;
  z-index: 1000;
}
#logo {
  padding: 20px;
}
#title {
  padding: 40px;
}
/* #desc-btn {
  padding: 20px;
} */

.home-container {
  position: relative;
  height: 100vh;
}

.home-top-box {
  display: flex;
  flex-direction: column;
  /* justify-content: start; */
}
.home-bottom-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

</style>