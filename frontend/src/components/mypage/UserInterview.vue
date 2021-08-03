<template>
  <div class="interview-info-box">
    <div>
      {{this.userInterview.companyName}} | 
      {{this.userInterview.interviewDate}} | 
      {{this.userInterview.interviewTime}}
    </div>
    <v-btn class="interview-button"
      color="white" text small
      @click="enterInterview"
    >
      입장
    </v-btn>
  </div>
</template>

<script>
export default {
    props: {
        userInterview: Object
    },
    methods: {
      enterInterview() {
        alert('인터뷰 입장')
      }
    }
}
</script>

<style>
.interview-info-box {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
    font-size: 20px;
}

.interview-button {
    width: 100px;
    background: #439474;
    margin-left: 10px
}
</style>