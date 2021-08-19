<template>
    <div>
        <img id="wanted-banner" src="/images/icons/wanted_banner.png" alt="banner.png">
        <CompanySearch />
        <CompanyList class="p-b-80" />
    </div>
</template>
<script>
import { mapState } from 'vuex';
import CompanyList from '@/components/wanted/CompanyList.vue'
import CompanySearch from '@/components/wanted/CompanySearch.vue'

export default {
    name: 'Wanted',
    components: {
        CompanyList,
        CompanySearch,
    },
    computed: {
        ...mapState('product', {
            totalProducts: state => state.totalProducts,
            page: state => state.page
        })
    },
    methods: {
        changePage(page) {
            this.$store.dispatch('product/setProducts', page);
        },
    },
}
</script>
<style scoped>
#wanted-banner {
  width: 100%;
  height: 100%;
  object-fit: cover;
  margin-bottom: 30px;
  z-index: 1000;
}
</style>