<template>
    <header class="header1">
        <!-- Header desktop -->
        <div class="container-menu-header">
            <div class="wrap_header">
                <!-- Logo -->
                <a href="" class="logo">
                    <img src="images/icons/logo.png" alt="IMG-LOGO">
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
                    <router-link :to="{name: 'mypage'}" tag="li" active-class="sale-not" exact>
                        <a href="#" class="header-wrapicon1 dis-block">
                            <img src="images/icons/icon-header-01.png" class="header-icon1" alt="ICON">
                        </a>
                    </router-link>
                    <span class="linedivide1"></span>
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
        }
    }
</script>