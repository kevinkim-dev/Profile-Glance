<template>
  <div class="row d-flex justify-content-center">
    <!-- <template v-for="company in companies">
      <div class="col-sm-12 col-md-6 col-lg-4 p-b-50">
        <Company :company="company" />
      </div>
    </template> -->
    <Company
      v-for="(company, idx) in companies"
      :key="idx+'company'"
      :company="company"
      />
  </div>
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
      // ...mapState('product', {
      //   products: state => state.products
      // })
      ...mapState('wanted', {
        companies: state => state.companies
      })
    },
    created() {
      this.$store.dispatch('product/setProducts');
    },
  }
</script>