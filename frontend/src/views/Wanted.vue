<template>
    <div>
        <!-- <section class="bg-title-page p-t-50 p-b-40 flex-col-c-m" :style="'background-image: url(https://picsum.photos/1920/239/?image=526);'">
            <h2 class="l-text2 t-center">
                Women
            </h2>
            <p class="m-text13 t-center">
                New Arrivals Women Collection 2018
            </p>
        </section> -->

        <CompanyList />
    </div>
</template>
<script>
    import { mapState } from 'vuex';

    export default {
        name: 'Wanted',
        components: {
            PriceFilter,
            CategoryFilter,
            ColorFilter,
            ProductList,
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
            }
        }
    }
</script>