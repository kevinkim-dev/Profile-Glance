<template>
    <div class="profile-image-whole">
        <div class="profile-image-box">
            <img class="profile-image" :src="imgSrc" alt="logo.png">
        </div>
        <div class="filebox imageEdit" v-if="isMyProfile">
            <label for="ex_file">사진 편집</label>
            <input type="file" id="ex_file" @change="changeImage" ref="img" accept="img/*" multiple >
        </div>
    </div>
</template>

<script>
import Axios from 'axios';

export default {
    data() {
        return {
            imgSrc: 'images/mypage/rabbit.jpg'
        }
    },
    props: {
        isMyProfile: true
    },
    computed: {
        isMe: function() {
            return (this.$store.state.userType == this.$store.state.mypage.profileType) &&
            (this.$store.state.userId == this.$store.state.mypage.profileId)
        },
    },
    methods: {
        changeImage: function(e) {
            const file = e.target.files[0]
            const userEmail = 'test5@test.com'
            const formData = new FormData();
            formData.append('userImg', file);
            formData.append('userEmail', userEmail);
            Axios.post('/user/uploadImg', formData, {
                baseURL: "http://localhost:8888/",
                headers: { 'Content-Type': 'multipart/form-data' }
            }).then( ({data}) => {
                console.log(data)
                this.imgSrc = URL.createObjectURL(e.target.files[0])
            })
            .catch( err => console.log(err))
        },
    }
}
</script>

<style>
.profile-image-whole {
    height: 300px;
    width: 300px;
}

.profile-image-box {
    position: relative;
    height: 300px;
    width: 300px;
    left: 80px;
    border-radius: 70%;
    overflow: hidden;
}

.imageEdit {
    position: relative;
    top: -20px;
    left: 270px;
}

.profile-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.filebox label { 
    display: inline-block; 
    padding: .5em .75em; 
    color: black; 
    font-size: inherit; 
    line-height: normal; 
    vertical-align: middle; 
    background-color: #fdfdfd; 
    cursor: pointer; 
    border: 1px solid #ebebeb; 
    border-radius: .25em; 
} 

.filebox input[type="file"] { 
    /* 파일 필드 숨기기 */ 
    position: absolute; 
    width: 1px; 
    height: 1px; 
    padding: 0; 
    margin: -1px; 
    overflow: hidden; 
    clip:rect(0,0,0,0); 
    border: 0; 
}


</style>