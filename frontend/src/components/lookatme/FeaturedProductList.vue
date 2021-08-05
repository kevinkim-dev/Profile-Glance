<template>
  <section class="newproduct bgwhite p-t-45 p-b-105">
    <div class="container">
      <div class="sec-title p-b-60">
        <v-row>
        <v-col cols="3">
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
        <v-col cols="8">
          <v-text-field
        v-model="test"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
        v-on:keypress.enter="searchMethod"
      ></v-text-field>
        </v-col>
        <v-col cols="1">
          <v-btn color="green"><router-link :to="{name: 'lookatmeregist'}" exact>업로드</router-link></v-btn>
        </v-col>
        </v-row>
      </div>
      <div>
        <v-row>
          <v-col cols="3">
            <v-list-group
              v-model="no1"
              prepend-icon="mdi-bottle-tonic-plus"
            >
          <template v-slot:activator>
            <v-list-item-title>이과</v-list-item-title>
          </template>
          <v-list-item
            v-for="(choose, i) in categories.no1"
            :key="i"
            link
          >
            <v-list-item-title v-text="choose.title" @click="selectCategory(1, choose.title)"></v-list-item-title>
          </v-list-item>
            </v-list-group>
          </v-col>
          <v-col cols="3">
            <v-list-group
              v-model="no2"
              prepend-icon="mdi-school"
          >
          <template v-slot:activator>
            <v-list-item-title>문과</v-list-item-title>
          </template>
          <v-list-item
            v-for="(choose, i) in categories.no2"
            :key="i"
            link
          >
            <v-list-item-title v-text="choose.title" @click="selectCategory(2, choose.title)"></v-list-item-title>
          </v-list-item>
        </v-list-group>
          </v-col>
          <v-col cols="3">
            <v-list-group
              v-model="no3"
              prepend-icon="mdi-content-cut"
          >
          <template v-slot:activator>
            <v-list-item-title>예체능</v-list-item-title>
          </template>
          <v-list-item
            v-for="(choose, i) in categories.no3"
            :key="i"
            link
          >
            <v-list-item-title v-text="choose.title" @click="selectCategory(3, choose.title)"></v-list-item-title>
          </v-list-item>
        </v-list-group>
          </v-col>
          <v-col cols="3">
            <v-list-group
              v-model="no4"
              prepend-icon="mdi-run"
          >
          <template v-slot:activator>
            <v-list-item-title>스포츠</v-list-item-title>
          </template>
          <v-list-item
            v-for="(choose, i) in categories.no4"
            :key="i"
            link
          >
            <v-list-item-title v-text="choose.title" @click="selectCategory(4, choose.title)"></v-list-item-title>
          </v-list-item>
        </v-list-group>
          </v-col>
        </v-row>
      </div>
      <div id="lookatme-view">
        <v-row>
          <v-col :cols="3" v-for="video in list" :key="video.lookatmeId" @click="lookatmeDetail(video.lookatmeId)">
            <v-card
              :loading="false"
              class="mx-2 my-12"
              max-width="374"
              height="400px"
            >
              <template slot="progress">
                <v-progress-linear
                  color="deep-purple"
                  height="10"
                  indeterminate
                ></v-progress-linear>
              </template>
              <v-img
                height="250"
                :src="getImg(video.thumbnail)"
              >
              </v-img>
              <!-- {{video.thumbnail}} -->
              <v-card-title><div class="title">{{video.title}}</div></v-card-title>
              <v-card-text>
                <v-row
                  align="center"
                  class="mx-0"
                >
                <div class="grey--text ms-4">게시일 : {{video.createdAt}}</div>
                </v-row>
                <div class="my-4 text-subtitle-1">
                  {{video.userNickName}}
                </div>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
        <infinite-loading :identifier="infiniteId" @infinite="infiniteHandler" spinner="circles"></infinite-loading>
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
  import { mapState } from 'vuex';
  import InfiniteLoading from 'vue-infinite-loading';
  import Product from '@/components/Product.vue';
  import http from '@/http.js';

  export default {
    computed: {
      ...mapState('product', {
        products: state => state.featuredProducts
      })
    },
    methods: {
      selectCategory(number, text) {
        switch(number) {
          case 1: {
            this.no1=false;
            break;
          }
          case 2: {
            this.no2=false;
            break;
          }
          case 3: {
            this.no3=false;
            break;
          }
          case 4: {
            this.no4=false;
            break;
          }
          default: break;
        }
        this.category=text;
        return false;
      },
      getImg(file){
        // console.log(file);
        // return require("@/../public/ServerFiles/Thumbnail/" + file);
      },
      searchMethod() {
        this.search=this.test;
        this.limit=0;
        this.list=[];
        this.infiniteId += 1;
        if(this.$refs.InfiniteLoading) {
          this.$refs.InfiniteLoading.stateChanger.reset();
        }
      },
      infiniteHandler($state) {
        console.log(this.search);
        if (this.search=="") {
          http.post('/lookatme/orderByView', {
            limit: this.limit,
          }).then((response) => {
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
        } else {
          if(this.select.code==="TITLE") {
            console.log(this.search + "," + this.select.code);
            http.post('/lookatme/searchByTitle', {
            title: this.search,
            limit: this.limit,
          }).then((response) => {
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
          } else if (this.select.code==="NICKNAME") {
            console.log("NICKNAME");
            http.post('/lookatme/searchByNickname', {
            userNickname: this.search,
            limit: this.limit,
          }).then((response) => {
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
          }
        } 
      },
      lookatmeDetail(lookatmeId) {
        console.log(lookatmeId);
      }
    },
    created() {
      this.$store.dispatch('product/setFeaturedProducts').then(() => {
        $(this.$refs.slick).slick2();
      });
    },
    components: {
      Product,
      InfiniteLoading
    },
    data() {
      return {
        list: [],
        no1: false,
        no2: false,
        no3: false,
        no4: false,
        infiniteId: +new Date(),
        categories: {
          no1: [
            {
              title: "개발"
            },
            {
              title: "테크"
            },
            {
              title: "교육"
            }
          ],
          no2: [
            {
              title: "서비스"
            },
            {
              title: "영업/마케팅"
            }
          ],
          no3: [
            {
              title: "춤"
            },
            {
              title: "연주"
            },
            {
              title: "노래"
            },
            {
              title: "연기"
            },
            {
              title: "영상"
            },
            {
              title: "코미디"
            },
            {
              title: "사진"
            },
            {
              title: "메이킹/만들기/손재주"
            },
            {
              title: "여행"
            },
            {
              title: "요리"
            },
            {
              title: "미술"
            },
            {
              title: "마술"
            }
          ],
          no4: [
            {
              title: "스포츠"
            },
            {
              title: "무술"
            },
            {
              title: "게임"
            }
          ]
        },
        category: "",
        limit: 0,
        isAll: true,
        test: "",
        isTitle: false,
        isNickName: false,
        search: "",
        select: {
          codeName: "제목",
          code: "TITLE"
        },
        kind: [
          {
              codeName: "제목",
              code: "TITLE"
          },
          {
            codeName: "닉네임",
            code: "NICKNAME"
          },
        ]
      }
    }
  }
</script>
<style>
#lookatme-view{
  overflow-y: scroll;
  height: 400px;
  width: 100%;
  overflow-x: hidden;
}
#lookatme-view::-webkit-scrollbar{
  display: none;
}
.title{
  display: block;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 230px;
  overflow: hidden;
}
</style>