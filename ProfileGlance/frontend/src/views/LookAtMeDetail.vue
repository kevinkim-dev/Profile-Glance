<template>
    <div>
        <detail :lookatme_id="$route.query.lookatme_id" :thumbnail="$route.query.thumbnail" :video="$route.query.video" />
    </div>
</template>

<script>
import Detail from '@/components/lookatme/Detail.vue'
export default {
    components: {
        Detail
    },
    
}
</script>

<style>

</style>