<template>
    <div>
        <modify :lookatme="lookatme" />
    </div>
</template>

<script>
import Modify from '@/components/lookatme/Modify.vue'
export default {
    components: {
        Modify
    },
    props: {
        lookatme: {
            type: [],
            default: null,
        }
    }
}
</script>

<style>

</style>