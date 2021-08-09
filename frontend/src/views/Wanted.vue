<template>
    <div>
        <!-- <section class="bg-title-page p-t-50 p-b-40 flex-col-c-m" :style="'background-image: url(https://picsum.photos/1920/239/?image=526);'">
            <h2 class="l-text2 t-center">
                WANTED
            </h2>
            <p class="m-text13 t-center">
                New Arrivals Women Collection 2018
            </p>
        </section> -->
        <CompanySearch />
        <CompanyList />
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