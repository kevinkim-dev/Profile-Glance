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

      <!-- Slide2 -->
      <div class="wrap-slick2">
        <div class="slick2" ref="slick">
          <template v-for="product in products">
            <Product :product="product" />
          </template>
        </div>
      </div>

    </div>
  </section>
</template>
<script>
  import { mapState } from 'vuex';

  import Product from '@/components/Product.vue';

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
    },
    created() {
      this.$store.dispatch('product/setFeaturedProducts').then(() => {
        $(this.$refs.slick).slick2();
      });
    },
    components: {
      Product
    },
    data() {
      return {
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