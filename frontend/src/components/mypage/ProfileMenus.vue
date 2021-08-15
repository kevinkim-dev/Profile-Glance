<template>
  <div class="profile-menu-box d-flex flex-column justify-content-center">
    <div class="d-flex justify-content-around pt-10">
        <v-btn class="red-button"
            color="white"
            text
            v-if="liked"
            @click="clickLike"
        >
            좋아요 취소
        </v-btn>
        <v-btn class="blue-button"
            color="white"
            text
            v-else
            @click="clickLike"
        >
            좋아요
        </v-btn>
        <v-btn class="green-button"
            color="white"
            text
            @click="$emit('openInterviewModal')"
        >
            면접 신청
        </v-btn>
    </div>
  </div>
</template>

<script>
import http from '@/http.js';

export default {
    data() {
        return {
            liked: Boolean
        }
    },
    methods: {
        clickLike: function() {
            //Axios통신(백앤드 구현 후 구현)
            //좋아요 누른 유저 테이블에 있으면 제거 없으면 추가
            const body = {
                'companyId': localStorage.getItem('id'),
                'userNickname': this.$route.params.id
            }
            http.post('/company/likecheckclick', body)
            .then(res => {
                console.log(res)
                if (this.liked == true) {
                    this.$emit('unliked')
                } else {
                    this.$emit('liked')
                }
                this.liked = !this.liked
            })
            .catch(err => {
                console.log(err)
            })
        }
    },
    mounted() {
        //Axios통신(백앤드 구현 후 구현)
        //해당 기업이 좋아요 누른 유저 목록을 받아오는 통신
        // const isLiked = (user) => user.userNickname == this.$route.params.id
        // this.liked = this.$store.state.likeUserList.some(isLiked)
        // console.log(this.liked)
        const body = {
            'companyId': localStorage.getItem('id'),
            'userNickname': this.$route.params.id
        }
        http.post('/company/likecheck', body)
        .then(res => {
            if (res.status == 201) {
                this.liked = false
            } else if (res.status == 202) {
                this.liked = true
            }
        })
        .catch(err => {
            console.log(err)
        })
    }

}
</script>

<style>
.profile-menu-box {
    margin-left: 80px;
    width: 300px;
    height: 100px;  
}

.blue-button {
    background: blue;
    width: 130px;
}

.green-button {
    background: green;
    width: 130px;
}

.red-button {
    background: red;
    width: 130px;
}
</style>