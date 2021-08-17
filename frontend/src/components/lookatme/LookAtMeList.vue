<template>
  <section class="newproduct bgwhite p-b-105">
    <img id="lookatme-banner" src="/images/icons/lookatme_banner.png" alt="banner.png">
    <div class="container px-0">
      <div class="sec-title">
        <v-row>
          <v-col cols="1" class="pb-0">
            <v-btn
            @click="changeCategoryVisible()">
              카테고리
            </v-btn>
          </v-col>
          <v-col cols="2" class="py-0">
            <v-select
              v-model="select"
              :items="kind"
              item-text="codeName"
              item-value="code"
              label="Select"
              return-object
              single-line
            ></v-select>
          </v-col>
          <v-col cols="8" class="py-0">
            <v-text-field
              v-model="test"
              append-icon="mdi-magnify"
              label="Search"
              single-line
              hide-details
              v-on:keypress.enter="searchMethod"
            ></v-text-field>
          </v-col>
          <v-col cols="1" class="pb-0">
            <v-btn
              color="#439474"
              class="white--text"
              v-if="isUser"
              @click="$router.push({ name: 'lookatmeregist' })"
              >업로드</v-btn
            >
          </v-col>
        </v-row>
      </div>
      <div v-if="categoryVisible" class="mb-5">
        <v-row>
          <v-col class="text-center p-0" cols="1" v-for="(category, i) in categories" :key="i">
            <v-btn
              depressed
              outlined
              style="min-width: 77px; border: thin #9A9A9A; color:#666666; box-shadow: 1px 1px 1px 1px gray;"
              class="ma-2"
              @click="categoryFilter(category.title)"
            >
              {{ category.title}}
            </v-btn>
          </v-col>
        </v-row>
        <hr>
      </div>
      <!-- <div class="mb-10">
        <v-row>
          <v-col cols="3">
            <v-list-group v-model="no1" prepend-icon="mdi-bottle-tonic-plus">
              <template v-slot:activator>
                <v-list-item-title>이과</v-list-item-title>
              </template>
              <v-list-item v-for="(choose, i) in categories.no1" :key="i" link>
                <v-list-item-title
                  v-text="choose.title"
                  @click="selectCategory(1, choose.title)"
                ></v-list-item-title>
              </v-list-item>
            </v-list-group>
          </v-col>
          <v-col cols="3">
            <v-list-group v-model="no2" prepend-icon="mdi-school">
              <template v-slot:activator>
                <v-list-item-title>문과</v-list-item-title>
              </template>
              <v-list-item v-for="(choose, i) in categories.no2" :key="i" link>
                <v-list-item-title
                  v-text="choose.title"
                  @click="selectCategory(2, choose.title)"
                ></v-list-item-title>
              </v-list-item>
            </v-list-group>
          </v-col>
          <v-col cols="3">
            <v-list-group v-model="no3" prepend-icon="mdi-content-cut">
              <template v-slot:activator>
                <v-list-item-title>예체능</v-list-item-title>
              </template>
              <v-list-item v-for="(choose, i) in categories.no3" :key="i" link>
                <v-list-item-title
                  v-text="choose.title"
                  @click="selectCategory(3, choose.title)"
                ></v-list-item-title>
              </v-list-item>
            </v-list-group>
          </v-col>
          <v-col cols="3">
            <v-list-group v-model="no4" prepend-icon="mdi-run">
              <template v-slot:activator>
                <v-list-item-title>스포츠</v-list-item-title>
              </template>
              <v-list-item v-for="(choose, i) in categories.no4" :key="i" link>
                <v-list-item-title
                  v-text="choose.title"
                  @click="selectCategory(4, choose.title)"
                ></v-list-item-title>
              </v-list-item>
            </v-list-group>
          </v-col>
        </v-row>
      </div> -->
      <div id="lookatme-view" style="height: 800px; width: 100%;">
        <v-row class="mt-2 ml-1">
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
                <!-- <v-chip
                  class="category"
                  color="#EAF5F1"
                  label
                  small
                  text-color="black"
                >
                  {{ video.category }}
                </v-chip> -->
                <v-img class="thumbnail" height="135" width="240" :src="getImg(video.thumbnail)"> </v-img>
                <div class="category">
                  {{ video.category }}
                </div>
              </div>
              <!-- {{video.thumbnail}} -->
              <v-card-title
                class="px-0"
                >
                <div class="title">
                  {{ video.title }}</div>
              </v-card-title>
              <v-card-text class="px-1">
                <v-row align="center" class="mx-0">
                  <!-- <div class="grey--text ms-4 created">게시일 : {{ $moment(video.createdAt).format("YYYY년 MMMM do dddd HH시 mm분") }}</div>
                  <div class="grey--text ms-4 created">{{ video.createdAt | moment("from", "now") }}</div> -->
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
            </v-card>
          </v-col>
        </v-row>
        <infinite-loading
          class="m-t-150"
          :identifier="infiniteId"
          @infinite="infiniteHandler"
          spinner="circles"
        >
          <div slot="no-more">
            <!-- <br>
            불러올 룩앳미가 없습니다</div> -->
          </div>
        <div slot="no-results">
          <br>
          불러올 룩앳미가 없습니다
        </div>
        </infinite-loading>
      </div>
      <!-- Slide2 -->
      <!-- <div class="wrap-slick2">
        <div class="slick2" ref="slick">
          <template v-for="product in products">
            <Product :product="product" />
          </template>
        </div>
      </div> -->
    </div>
  </section>
</template>
<script>
import Vue from 'vue';
import { mapState, mapGetters } from 'vuex';
import InfiniteLoading from 'vue-infinite-loading';
import Product from '@/components/Product.vue';
import http from '@/http.js';
import VueMoment from 'vue-moment';
import moment from 'moment';
moment.locale('ko');
Vue.use(VueMoment, {moment});
export default {
  computed: {
    ...mapState('product', {
      products: (state) => state.featuredProducts,
      isUser() {
        return localStorage.getItem('login_type') == 'user'
      }
    }),
    ...mapGetters([
        'fileURL'
      ]),
  },
  methods: {
    changeCategoryVisible () {
      this.categoryVisible = !this.categoryVisible
    },
    categoryFilter (category) {
      this.category = category;
      this.categoryVisible = false
      this.limit = 0;
      this.list = [];
      this.infiniteId += 1;
      if (this.$refs.InfiniteLoading) {
        this.$refs.InfiniteLoading.stateChanger.reset();
      }
      return false;
    },
    selectCategory(number, text) {
      switch (number) {
        case 1: {
          this.no1 = false;
          break;
        }
        case 2: {
          this.no2 = false;
          break;
        }
        case 3: {
          this.no3 = false;
          break;
        }
        case 4: {
          this.no4 = false;
          break;
        }
        default:
          break;
      }
      this.category = text;
      this.limit = 0;
      this.list = [];
      this.infiniteId += 1;
      if (this.$refs.InfiniteLoading) {
        this.$refs.InfiniteLoading.stateChanger.reset();
      }
      return false;
    },
    getImg(file) {
      return this.fileURL + 'ServerFiles/Thumbnail/' + file;
      },
    getUserPic(file) {
      return this.fileURL + 'ServerFiles/UserImg/' + file;
    },
    searchMethod() {
      this.search = this.test;
      this.limit = 0;
      this.list = [];
      this.infiniteId += 1;
      if (this.$refs.InfiniteLoading) {
        this.$refs.InfiniteLoading.stateChanger.reset();
      }
    },
    infiniteHandler($state) {
      if (this.search == '' && this.category == '') {
        
        http
          .post('/lookatme/orderByView', {
            limit: this.limit,
          })
          .then((response) => {
            setTimeout(() => {
              if (response.data.length) {
                this.list = this.list.concat(response.data);
                this.limit += 16;
                $state.loaded();
              } else {
                $state.complete();
              }
            }, 1000);
          })
          .catch((error) => {});
      } else if (this.search != '' && this.category == '') {
        
        if (this.select.code === 'TITLE') {
          console.log(this.search + ',' + this.select.code);
          http
            .post('/lookatme/searchByTitle', {
              title: this.search,
              limit: this.limit,
            })
            .then((response) => {
              setTimeout(() => {
                if (response.data.length) {
                  this.list = this.list.concat(response.data);
                  this.limit += 16;
                  $state.loaded();
                  console.log(this.list);
                } else {
                  $state.complete();
                }
              }, 1000);
            })
            .catch((error) => {});
        } else if (this.select.code === 'NICKNAME') {
          console.log('NICKNAME');
          http
            .post('/lookatme/searchByNickname', {
              userNickname: this.search,
              limit: this.limit,
            })
            .then((response) => {
              setTimeout(() => {
                if (response.data.length) {
                  this.list = this.list.concat(response.data);
                  this.limit += 16;
                  $state.loaded();
                  console.log(this.list);
                } else {
                  $state.complete();
                }
              }, 1000);
            })
            .catch((error) => {});
        }
      }
      else if (this.category != '') {
        if(this.category == '전체'){
          http
          .post('/lookatme/orderByView', {
            limit: this.limit,
          })
          .then((response) => {
            setTimeout(() => {
              if (response.data.length) {
                this.list = this.list.concat(response.data);
                this.limit += 16;
                $state.loaded();
              } else {
                $state.complete();
              }
            }, 1000);
          })
          .catch((error) => {});
        }else{
          http
          .post('/lookatme/searchByCategory', {
            category: this.category,
            limit: this.limit,
          })
          .then((response) => {
            setTimeout(() => {
              if (response.data.length) {
                this.list = this.list.concat(response.data);
                this.limit += 16;
                $state.loaded();
              } else {
                $state.complete();
              }
            }, 1000);
          })
          .catch((error) => {});
        }
        
      this.test = '';
      this.search = '';
      this.category = '';
      }
    },
    lookatmeDetail(lookatmeId, thumbnail, video) {
      let lookatme_id = lookatmeId + "";
      console.log(video);
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
      categoryVisible: false,
      list: [],
      no1: false,
      no2: false,
      no3: false,
      no4: false,
      infiniteId: +new Date(),
      categories: [
        {
          title: '전체',
        },
          {
            title: '개발',
          },
          {
            title: '테크',
          },
          {
            title: '교육',
          },
          {
            title: '서비스',
          },
          {
            title: '춤',
          },
          {
            title: '연주',
          },
          {
            title: '노래',
          },
          {
            title: '연기',
          },
          {
            title: '영상',
          },
          {
            title: '코미디',
          },
          {
            title: '사진',
          },
          {
            title: '손재주',
          },
          {
            title: '여행',
          },
          {
            title: '요리',
          },
          {
            title: '미술',
          },
          {
            title: '마술',
          },
          {
            title: '스포츠',
          },
          {
            title: '무술',
          },
          {
            title: '게임',
          },
          {
            title: '영업/마케팅',
          },
      ],
      category: '',
      limit: 0,
      isAll: true,
      test: '',
      isTitle: false,
      isNickName: false,
      search: '',
      select: {
        codeName: '제목',
        code: 'TITLE',
      },
      kind: [
        {
          codeName: '제목',
          code: 'TITLE',
        },
        {
          codeName: '닉네임',
          code: 'NICKNAME',
        },
      ],
    };
  },
};
</script>
<style>
#lookatme-view {
  overflow-y: scroll;
  overflow-x: hidden;
  margin-right: 15px;
  margin-left: 0px;
}
#lookatme-view::-webkit-scrollbar {
  display: none;
}
.lookatme {
  border: black solid 1px;
}
.thumbnail {
  position: absolute;
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

#lookatme-banner {
  width: 100%;
  height: 100%;
  object-fit: cover;
  margin-bottom: 30px;
}
</style>
