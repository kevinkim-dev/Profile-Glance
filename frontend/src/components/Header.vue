<template>
    <header class="header1">
        <!-- Header desktop -->
        <div class="container-menu-header">
            <div class="wrap_header">
                <!-- Logo -->
                <div @click="clickLogo">
                    <img class="header-logo" src="/images/icons/logo.png" alt="IMG-LOGO">
                    <img class="header-logo-letter" src="/images/icons/logo_letter.png" alt="IMG-LOGO">
                </div>
                <!-- Menu -->
                <div class="wrap_menu mt-1">
                    <nav class="menu">
                        <ul class="main_menu">
                            <router-link :to="{name: 'lookatme'}" tag="li" exact class="menu-font py-0">
                                룩앳미
                            </router-link>
                            <router-link :to="{name: 'wanted'}" tag="li" exact class="menu-font py-0">
                                원티드
                            </router-link>
                        </ul>
                    </nav>
                </div>
                <!-- Header Icon -->
                <div class="header-icons">
                    <ul class="main_menu">
                        <i @click="clickLogout" class="fas fa-sign-out-alt logout-font me-5"></i>              
                        <div class="header-image-box ms-5" @click="clickMypage">
                            <img :src="getImg()" class="header-image" alt="profile_img">
                        </div>
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
		}),
        ...mapGetters([
            'fileURL',
        ])
	},
	methods: {
		clickLogout() {
            Swal.fire({ 
                icon: 'question', // Alert 타입 
                title: '로그아웃 하시겠어요?', // Alert 제목 
                showCancelButton: true,
                showConfirmButton: false,
                showDenyButton: true,
                denyButtonText: `로그아웃`,
                cancelButtonText: `아니오`,
            })
            .then((res) => {
                if(res.isDenied) {
                    return this.logout()
                }
            })
		},
		logout() {
			this.$store.dispatch('requestLogout')
			this.$router.push('/')
			location.reload()
		},
		clickMypage() {
			const info = {
				'profileType': localStorage.getItem('login_type'),
				'id': localStorage.getItem('id')
			}
			this.$store.dispatch('mypage/getUserData', info)
			this.$router.push('/mypage/' + info.profileType + '/' + info.id)
		},
		getImg() {
            if (localStorage.getItem('login_type') == 'user') {
                return (
                    this.fileURL + 'ServerFiles/UserImg/' +
                    localStorage.getItem('profile')
                )
            } else {
                return (
                    this.fileURL + 'ServerFiles/CompanyLogo/' +
                    localStorage.getItem('profile')
                )
            }
        },
        clickLogo() {
            this.$router.push('/lookatme')
        }
	}
}
</script>
<style scoped>
li.router-link-exact-active{
    color: #439474;
    font-weight: bold;
}

.menu-font {
    font-size: 16px;
}


.logout-font {
    font-size: 25px;
}

.menu-font,
.logout-font,
.header-image:hover {
    cursor: pointer;
}

.header-image-box {
    height: 35px;
    width: 35px;
    border-radius: 70%;
    overflow: hidden;
}

.header-image {
    width: 100%;
    height: 100%;   
    object-fit: cover;
}

.header-logo-box {
    height: 30px;
    width: 200px;
}

.header-logo {
    height: 25px;
    width: 40px;
    object-fit: cover;
    background: none;
    margin-left: 20px;
    margin-right: 3px;
}

.header-logo-letter {
    height: 25px;
    width: 170px; 
    object-fit: cover;
    background: none;
}

.header-logo,
.header-logo-letter:hover {
    cursor: pointer;
}
</style>
