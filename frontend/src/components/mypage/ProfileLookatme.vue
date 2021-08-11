<template>
  <section class="newproduct bgwhite">
    <div class="container">
      <div id="lookatme-view">
        <v-row>
          <v-col
            :cols="3"
            v-for="video in list"
            :key="video.lookatmeId"
            @click="lookatmeDetail(video.lookatmeId)"
          >
            <v-card :loading="false" class="mx-2 my-12" max-width="374" height="400px">
              <template slot="progress">
                <v-progress-linear
                  color="deep-purple"
                  height="10"
                  indeterminate
                ></v-progress-linear>
              </template>
              <v-img height="250" :src="getImg(video.thumbnail)"> </v-img>
              <!-- {{video.thumbnail}} -->
              <v-card-title
                ><div class="title">{{ video.title }}</div></v-card-title
              >
              <v-card-text>
                <v-row align="center" class="mx-0">
                  <div class="grey--text ms-4">게시일 : {{ video.createdAt }}</div>
                </v-row>
                <div class="my-4 text-subtitle-1">
                  {{ video.userNickName }}
                </div>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
        <infinite-loading class="m-t-50"
          :identifier="infiniteId"
          @infinite="infiniteHandler"
          spinner="circles"
        ></infinite-loading>
      </div>   
    </div>
  </section>
</template>
<script>
import { mapState, mapGetters } from 'vuex';
import InfiniteLoading from 'vue-infinite-loading';
import Product from '@/components/Product.vue';
import http from '@/http.js';

export default {
  computed: {
    ...mapState('product', {
      products: (state) => state.featuredProducts,
    }),
    ...mapGetters([
        'fileURL'
      ]),
  },
  methods: {
    getImg(file) {
      return this.fileURL + 'ServerFiles/Thumbnail/' + file;
    },
    infiniteHandler($state) {
      http
        .post('/lookatme/searchByNickname', {
          userNickname: this.$route.params.id,
          limit: this.limit,
        })
        .then((response) => {
          setTimeout(() => {
            if (response.data.length) {
              this.list = this.list.concat(response.data);
              this.limit += 10;
              $state.loaded();
              console.log(this.list);
            } else {
              $state.complete();
            }
          }, 1000);
        })
        .catch((error) => {});
    },
    lookatmeDetail(lookatmeId) {
      console.log(lookatmeId);
      let lookatme_id = lookatmeId + "";
      this.$router.push({name: 'lookatmedetail', query: {lookatme_id: lookatme_id}})
    },
  },
  created() {
    this.$store.dispatch('product/setFeaturedProducts').then(() => {
      $(this.$refs.slick).slick2();
    });
  },
  components: {
    Product,
    InfiniteLoading,
  },
  data() {
    return {
      list: [],
      no1: false,
      no2: false,
      no3: false,
      no4: false,
      infiniteId: +new Date(),
      limit: 0,
      isAll: true,
      test: '',
      isTitle: false,
      isNickName: false,
      search: '',
    };
  },
};
</script>
<style>
#lookatme-view {
  overflow-y: scroll;
  height: 400px;
  width: 100%;
  overflow-x: hidden;
}
#lookatme-view::-webkit-scrollbar {
  display: none;
}
.title {
  display: block;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 230px;
  overflow: hidden;
}
</style>
