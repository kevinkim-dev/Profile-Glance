<template>
    <v-btn class="blue-button"
        color="white"
        text
        v-if="isMe"
    >
        회원정보 수정
    </v-btn>
    <v-btn class="blue-button"
        color="white"
        text
        v-else
    >
        좋아요
    </v-btn>
</template>

<script>
export default {
    props: {
        isMe: 0,
    }
}
</script>

<style>
.blue-button {
    background: blue;
    width: 130px;
}
</style>