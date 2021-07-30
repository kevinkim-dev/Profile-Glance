<template>
    <v-btn class="red-button"
        color="white"
        text
        v-if="isMe"
        @click="clickDeleteButton"
    >
        회원 탈퇴
    </v-btn>
</template>

<script>
export default {
    props: {
        isMe: 0,
    },
    methods: {
        clickDeleteButton() {
            this.$store.dispatch('requestDeleteUser')
        }
    }
}
</script>

<style>
.red-button {
    background: crimson;
    width: 130px;
}
</style>