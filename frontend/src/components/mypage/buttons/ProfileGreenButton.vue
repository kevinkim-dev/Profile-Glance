<template>
    <v-btn class="green-button"
        color="white"
        text
        v-if="isInterviewAvailable"
    >
        면접 신청
    </v-btn>
</template>

<script>
export default {
    props: {
        isInterviewAvailable: false
    }
}
</script>

<style>
.green-button {
    background: green;
    width: 130px;
}
</style>