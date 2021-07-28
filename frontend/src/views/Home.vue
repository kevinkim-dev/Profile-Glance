<template>
<v-container>
  <div>
  <v-row justify="end" id="login-btn">
    <v-dialog
      v-model="isLoginViewd"
      persistent
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
      <login @close="closeLogin" @signup="openSignUp"/>
    </v-dialog>
    <v-dialog
      v-model="isSignUpViewd"
      persistent
      max-width="600px"
    > <sign-up @close="closeSignUp" @login="openLogin" />
    </v-dialog>
  </v-row>
  
<v-row justify="center" id="logo">
    <v-img
      max-height="150"
      max-width="250"
      src="images/icons/pgpg.png"
    ></v-img>
</v-row>
<v-row justify="center" id="title">
    <v-img
      height="140"
      width="200"
      src="images/icons/pg.png"
    ></v-img>
</v-row>
<v-row justify="center" class="desc-btn">
  <button type="button" id="down-btn">
  <v-img
      max-height="150"
      max-width="250"
      src="images/icons/down.png"
    ></v-img>
  </button>
</v-row>
  </div>
<v-row justify="center">
<div id="desc">
  <v-row justify="center" class="desc-btn" id="up-btn">
    <button type="button">
  <v-img
      max-height="150"
      max-width="250"
      src="images/icons/down.png"
      style="transform:rotate(180deg)"
    ></v-img>
    </button>
</v-row>
<v-container>
  <v-row justify="center">
  Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita et eum quaerat dolore pariatur laudantium inventore voluptatem minima consectetur amet corrupti fugit deserunt eos porro, dignissimos qui blanditiis quidem aliquid.
  </v-row>
</v-container>
</div>
</v-row>
</v-container>
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
    console.log("hi");
    $("#down-btn").on("click", function(event) {
      console.log("click");
      var offset = $("#desc").offset();
      console.log(offset);
      $("html").animate({scrollTop:offset.top}, 1800);
    });
    $("#up-btn").on("click", function(event) {
      console.log("click");
      var offset = $("#login-btn").offset();
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
#desc {
  position: absolute;
  top: 1000px;
}
#login-btn {
  padding: 30px;
}
#logo {
  padding: 20px;
}
#title {
  padding: 20px;
}
#desc-btn {
  padding: 20px;
}
</style>