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
      <login @close="isLoginViewd=false" @signup="isSigUpViewd=true;isLoginViewd=false"/>
    </v-dialog>
    <v-dialog
      v-model="isLoginViewd"
      persistent
      max-width="600px"
    >
      <login @close="isLoginViewd=false" />
    </v-dialog>
    <v-dialog
      v-model="isSignUpViewd"
      persistent
      max-width="600px"
    > <sign-up @close="isSignUpViewd=false" />
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
  }
</script>

<style>

</style>