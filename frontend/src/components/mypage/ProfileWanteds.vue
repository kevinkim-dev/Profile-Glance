<template>
  <div class="profile-wanted-box">
    <ProfileWanted
      v-for="(company, idx) in companies"
      :key="idx+'company'"
      :company="company"
      />
  </div>
</template>
<script>
  import { mapState } from 'vuex'
  import ProfileWanted from '@/components/mypage/ProfileWanted.vue'

  export default {
    name: 'CompanyList',
    components: {
      ProfileWanted,
    },
    computed: {
      ...mapState('wanted', {
        companies: state => state.companies
      })
    },
    created() {
      this.$store.dispatch('wanted/setCompanies')
    },
  }
</script>

<style>
.profile-wanted-box {
  background-color: #eee;
  padding: 30px;
  height: 100%;
  overflow: auto;
  display: flex;
  justify-content: space-around;
}
</style>