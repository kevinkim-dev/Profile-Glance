<template>
  <v-row class="row d-flex" style="max-width:60vw; margin:auto;">
    <Company
      v-for="(company, idx) in companies"
      :key="idx+'company'"
      :company="company"
      />
  </v-row>
</template>
<script>
  import { mapState } from 'vuex'
  import Company from '@/components/wanted/Company.vue'

  export default {
    name: 'CompanyList',
    components: {
      Company,
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