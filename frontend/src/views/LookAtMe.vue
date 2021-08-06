<template>
  <div class="home">
    <!-- <SlideBanner /> -->
    <!-- <BestProductList /> -->
    <FeaturedProductList />
    <!-- <MiddleBanner />
    <BlogArticles />
    <InstagramArticles /> -->
  </div>
</template>

<script>
import SlideBanner from '@/components/lookatme/SlideBanner.vue';
import FeaturedProductList from '@/components/lookatme/FeaturedProductList.vue';

export default {
  name: 'lookatme',
  components: {
    SlideBanner,
    FeaturedProductList,
  },
};
</script>
