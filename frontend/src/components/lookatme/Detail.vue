<template>
    <v-container v-if="lookatme">
        <v-row>
            <v-col cols='8'>
                영상
            </v-col>
            <v-col cols='4'>
                <div id="video_content">
                    <div id="video_main">
                        <h1>{{lookatme.title}}</h1><br>
                        {{lookatme.createdAt}}<br>
                        <br>
                        <a>{{lookatme.userNickName}}</a><br>
                        <br>
                        {{lookatme.content}}<br>
                    </div>
                </div>
                <div v-show="isCreator">
                    <v-btn block text x-large class="primary-color text-white rounded-0" >수정</v-btn>
                </div>
            </v-col>
        </v-row>
    <br>
    <div>
        관련 영상 목록
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
        <infinite-loading
        v-if="lookatme"
          @infinite="infiniteHandler"
          spinner="circles"
        ></infinite-loading>
      </div>
    </div>
    </v-container>
</template>

<script>
import { mapGetters } from 'vuex';
import http from '@/http.js';
import InfiniteLoading from 'vue-infinite-loading';
export default {
    computed: {
    ...mapGetters([
        'fileURL'
      ]),
  },
    components: {
        InfiniteLoading
    },
    props: {
        lookatme_id: {
            type: String,
            default: '',
        }
    },
    data() {
        return {
            lookatme: null,
            category: '',
            list: [],
            limit: 0,
            isCreator: false,
        }
    },
    async created() {
        await http
            .get('/lookatme/detailLookatme/' + this.lookatme_id)
            .then(response => {
                this.lookatme = response.data;
                this.category = response.data.category;
            })
            .catch((error) => {});
            // if (this.lookatme.userNickName == this.$store.state.data.userData.userNickname)
            if (this.lookatme.userNickName == localStorage.getItem('id'))
                this.isCreator = true;
    },

    methods: {
    getImg(file) {
      return this.fileURL + 'ServerFiles/Thumbnail/' + file;
    },
    infiniteHandler($state) {
        let c = this.lookatme.category;
        console.log(this.lookatme);
        http
          .post('/lookatme/searchByCategory', {
            category: this.category,
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
        }
    }
}
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
#video_content {
background: rgba(0, 0, 0, 0.1);
  overflow-y: scroll;
  height: 300px;
  width: 100%;
  overflow-x: hidden;
  padding: 10px;
}
#video_content::-webkit-scrollbar {
  display: none;
}
#video_main{
    margin: 15px;
}
</style>