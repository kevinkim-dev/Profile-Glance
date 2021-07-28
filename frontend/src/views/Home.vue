<template>
  <v-row justify="center">

    <v-dialog
      v-model="isLoginViewd"
      persistent
      max-width="600px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="primary"
          dark
          v-bind="attrs"
          v-on="on"
        >
          Open Dialog
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
        }
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

</style>