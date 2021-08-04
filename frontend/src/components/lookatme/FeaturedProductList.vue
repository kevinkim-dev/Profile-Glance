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
        v-model="search"
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
                :src="video.thumbnail"
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
        <infinite-loading @infinite="infiniteHandler" spinner="circles"></infinite-loading>
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
      searchMethod() {
        console.log("search");
        console.log(this.search);
        console.log(this.select);
      },
      infiniteHandler($state) {
        http.post('/lookatme/orderByView', {
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
        limit: 0,
        search: "",
        select: {
          codeName: "전체",
          code: "ALL"
        },
        kind: [
          {
            codeName: "전체",
            code: "ALL"
          },
          {
              codeName: "제목",
              code: "TITLE"
          },
          {
            codeName: "닉네임",
            code: "NICKNAME"
          },
          {
            codeName: "작성자",
            code: "USER"
          }
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
.title{
  display: block;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 230px;
  overflow: hidden;
}
</style>