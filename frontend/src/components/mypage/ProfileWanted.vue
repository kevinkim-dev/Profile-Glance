<template>
  <v-card
    elevation="1"
    outlined
    class="m-2 d-flex align-center profile-wanted"
  >
    <div class="d-flex flex-column">
      <div class="d-flex justify-content-around">
        <span>{{ company.career }}</span>
        <span>[{{ company.job }}] {{ company.jobDetail }}</span>
      </div>
      <div class="d-flex">
        <v-chip label v-if="company.presentationDate">
          {{ company.presentationDate | moment('MM월 DD일 HH:mm') }}
        </v-chip>
      </div>
    </div>
    <v-btn class="interview-button"
      color="white" text x-large
      @click="$router.push('/presentation/' + company.companyName)"
    >
      설명회개설
    </v-btn>
  </v-card>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'Company',
  props: ['company'],
  computed: {
    ...mapGetters([
      'fileURL'
    ])
  },
  methods: {
    getImgPath: function() {
      return this.fileURL + 'ServerFiles/CompanyLogo/' + this.company.companyImg;
    },
  },
};
</script>

<style>
h5 {
  margin-top: 5px;
  margin-bottom: 5px;
}

img {
  background: white;
  width: 100px;
  height: 100px;
  object-fit: cover;
}

.profile-wanted {
  width: 300px;
  height: 100px;
}

.companyLogo {
  width: 100px;
  height: 100px;
}

.interview-button {
    width: 100px;
    background: #439474;
    margin-left: 10px
}

</style>
