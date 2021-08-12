<template>
  <div class="home">
    <!-- <SlideBanner /> -->
    <!-- <BestProductList /> -->
    <LookatmeList />
    <!-- <MiddleBanner />
    <BlogArticles />
    <InstagramArticles /> -->
  </div>
</template>

<script>
import LookatmeList from '@/components/lookatme/LookAtMeList.vue';

export default {
  name: 'lookatme',
  components: {
    LookatmeList,
  },
};
</script>
