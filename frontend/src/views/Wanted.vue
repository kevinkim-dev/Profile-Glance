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
        <div class="d-flex justify-end">
            <v-btn text large class="m-3 primary-color text-white rounded-0" @click="$router.push({ name: 'wantedregist' })">채용 공고 등록</v-btn>
        </div>
        <CompanyList />
    </div>
</template>
<script>
import { mapState } from 'vuex';
import CompanyList from '@/components/wanted/CompanyList.vue'

export default {
    name: 'Wanted',
    components: {
        CompanyList,
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