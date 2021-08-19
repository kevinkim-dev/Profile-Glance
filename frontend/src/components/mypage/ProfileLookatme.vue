<template>
  <section class="newproduct bgwhite">
    <div class="container">
      <span class="text-center p-2" style="border-radius: 5px; background: #eaf5f1">{{profileId}}님의 룩앳미</span>
      <div class="m-t-30">
        <v-row>
          <v-col
            :cols="3"
            v-for="video in list"
            :key="video.lookatmeId"
            @click="lookatmeDetail(video.lookatmeId, video.thumbnail, video.video)"
          >
            <v-card :loading="false" class="lookatme m-0" width="260" height="280px" style="padding: 10px; box-shadow: -2px -2px .4em #a0a0a0, 2px 2px .4em #a0a0a0;">
              <template slot="progress">
                <v-progress-linear
                  color="deep-purple"
                  height="10"
                  indeterminate
                ></v-progress-linear>
              </template>
              <div style="position: relative;">
                <v-img class="thumbnail" height="135" width="240" :src="getImg(video.thumbnail)"> </v-img>
                <div class="category">
                  {{ video.category }}
                </div>
              </div>
              <div class="card-text">
                <v-card-title
                  class="px-0"
                  >
                  <div class="title">
                    {{ video.title }}</div>
                </v-card-title>
                <v-card-text class="px-1">
                  <v-row align="center" class="mx-0">
                  </v-row>
                  <div class="my-4 text-subtitle-1">
                    <div class="d-flex">
                      <div class="chat-image-box mr-2">
                          <img :src="getUserPic(video.userImg)" class="chat-image" alt="profile_img">
                      </div>
                      <div>
                        {{ video.userNickName }}
                      </div>
                    </div>
                    <div class="d-flex justify-content-between my-1">
                      <div>
                        <i class="far fa-eye"></i> {{video.view}}  
                      </div>
                      <div>
                        {{ video.createdAt | moment("from", "now") }}
                      </div>  
                    </div>
                  </div>
                </v-card-text>
              </div>
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
import Vue from 'vue';
import VueMoment from 'vue-moment';
import moment from 'moment';
moment.locale('ko');
Vue.use(VueMoment, {moment});

export default {
  props: {
    profileId: String
  },
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
    getUserPic(file) {
      return this.fileURL + 'ServerFiles/UserImg/' + file;
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
            } else {
              $state.complete();
            }
          }, 1000);
        })
        .catch((error) => {});
    },
    lookatmeDetail(lookatmeId, thumbnail, video) {
      let lookatme_id = lookatmeId + "";
      this.$router.push({name: 'lookatmedetail', query: {lookatme_id: lookatme_id, thumbnail: thumbnail, video: video}});
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
.title:hover {
  overflow: visible;
}
.created {
  display: block;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 230px;
  overflow: hidden;
}
.created:hover {
  overflow: visible;
  background-color: white;
}

.lookatme {
  border: black solid 1px;
}
.thumbnail {
  position: relative;
  width: 100%;
  height: 100%;
}
.category {
  position: absolute;
  top: 0px;
  left: 0%;
  background-color:#EAF5F1;
  padding-left: 5px;
  padding-right: 5px;
  border-bottom-right-radius: 4px;
  opacity: 0.9;
}

.title {
  display: block;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 230px;
  overflow: hidden;
}
.title:hover {
  overflow: visible;
}
.created {
  display: block;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 230px;
  overflow: hidden;
}
.created:hover {
  overflow: visible;
  background-color: white;
}

.lookatme:hover {
  cursor: pointer;
}
.chat-image-box {
    height: 20px;
    width: 20px;
    border-radius: 70%;
    overflow: hidden;
}
.chat-image {
    width: 100%;
    height: 100%;   
    object-fit: cover;
}
.card-text {
    position: relative;
}

#lookatme-banner {
  width: 100%;
  height: 100%;
  object-fit: cover;
  margin-bottom: 30px;
  z-index: 1000;
}
</style>
