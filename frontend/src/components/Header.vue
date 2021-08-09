<template>
    <header class="header1">
        <!-- Header desktop -->
        <div class="container-menu-header">
            <div class="wrap_header">
                <!-- Logo -->
                <a href="" class="logo">
                    <img src="/images/icons/pgpg.png" alt="IMG-LOGO">
                </a>
                <!-- Menu -->
                <div class="wrap_menu">
                    <nav class="menu">
                        <ul class="main_menu">
                            <router-link :to="{name: 'lookatme'}" tag="li" active-class="sale-noti" exact>
                                <a>룩앳미</a>
                            </router-link>
                            <router-link :to="{name: 'wanted'}" tag="li" active-class="sale-noti" exact>
                                <a>원티드</a>
                            </router-link>
                            <router-link :to="{name: 'features'}" tag="li" active-class="sale-not" exact>
                                <a>커뮤니티</a>
                            </router-link>
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