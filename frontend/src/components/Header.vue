<template>
    <header class="header1">
        <!-- Header desktop -->
        <div class="container-menu-header">
            <div class="wrap_header">
                <!-- Logo -->
                <img style="max-width: 100px; background: none;" class="img-fluid" src="/images/icons/pgpg.png" alt="IMG-LOGO">
                <!-- Menu -->
                <div class="wrap_menu">
                    <nav class="menu">
                        <ul class="main_menu">
                            <router-link :to="{name: 'lookatme'}" tag="li" exact>
                                <a>룩앳미</a>
                            </router-link>
                            <router-link :to="{name: 'wanted'}" tag="li" exact>
                                <a>원티드</a>
                            </router-link>
                            <!-- <router-link :to="{name: 'features'}" tag="li" exact>
                                <a>커뮤니티</a>
                            </router-link> -->
                        </ul>
                    </nav>
                </div>
                <!-- Header Icon -->
                <div class="header-icons">
                    <ul class="main_menu">
                        <a @click="clickLogout">로그아웃</a>              
                        <span class="linedivide1"></span>
                        <a @click="clickMypage">마이페이지</a>              
                        <!-- <router-link :to="{name: 'mypage'}" tag="li" active-class="sale-not" exact>
                            <a>마이페이지</a> 
                        </router-link> -->
                    </ul>
                </div>
            </div>
        </div>
    </header>
</template>
<script>
    import { mapState, mapGetters } from 'vuex';

    export default {
        computed: {
            ...mapState('cart', {
                cartItems: state => state.items
            }),
            ...mapGetters('cart', {
                totalCartPrice: 'totalPrice',
                totalCartQty: 'totalQty'
            })
        },
        methods: {
            clickLogout() {
                localStorage.removeItem('token')
                localStorage.removeItem('login_type')
                localStorage.removeItem('id')
                this.$router.push('/')
                this.$store.dispatch('requestLogout')
                location.reload()
            },
            clickMypage() {
                this.$store.dispatch('mypage/showMypage', this.$store.state)
                this.$router.push('/mypage')
            }
        }
    }
</script>
<style>
li.router-link-exact-active > a {
    color: #439474;
    font-weight: bold;
}
/* header {
    background-color: #C0DDD1;
} */
</style>