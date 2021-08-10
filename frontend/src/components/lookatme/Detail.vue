<template>
    <v-container v-if="lookatme">
        <v-row>
            <v-col cols='8'>
                <div class="item">
                    <div class="player">
                        <video-player  class="vjs-custom-skin"
                         ref="videoPlayer"
                         :options="playerOptions"
                         :playsinline="true"
                         @play="onPlayerPlay($event)"
                         @pause="onPlayerPause($event)"
                         @ended="onPlayerEnded($event)"
                         @loadeddata="onPlayerLoadeddata($event)"
                         @waiting="onPlayerWaiting($event)"
                         @playing="onPlayerPlaying($event)"
                         @timeupdate="onPlayerTimeupdate($event)"
                         @canplay="onPlayerCanplay($event)"
                         @canplaythrough="onPlayerCanplaythrough($event)"
                         @ready="playerReadied"
                         @statechanged="playerStateChanged($event)">
                        </video-player>
                    </div>
                </div>
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
                    <v-btn block text x-large class="primary-color text-white rounded-0" @click="modifyLookatme">수정</v-btn>
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
            v-if="lookatme.lookatmeId != video.lookatmeId"
            @click="lookatmeDetail(video.lookatmeId, video.thumbnail, video.video)"
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
import 'video.js/dist/video-js.css';
import { videoPlayer } from 'vue-video-player';
export default {
    computed: {
    ...mapGetters([
        'fileURL'
      ]),
      player() {
          return this.$refs.videoPlayer.player
      }
  },
    components: {
        InfiniteLoading,
        videoPlayer
    },
    props: {
        lookatme_id: {
            type: String,
            default: '',
        },
        thumbnail: {
            type: String,
            default: '',
        },
        video: {
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
             playerOptions: {
          height: '352',
          width: '780',
          autoplay: true,
          muted: true,
          language: 'en',
          playbackRates: [0.7, 1.0, 1.5, 2.0],
          sources: [{
            type: "video/mp4",
            // src: "http://vjs.zencdn.net/v/oceans.mp4",
            // src: this.fileURL + "ServerFiles/Video/" + this.video,
            src: this.$store.getters.fileURL + "ServerFiles/Video/" + this.video,
          }],
          poster: this.$store.getters.fileURL + "ServerFiles/Thumbnail/" + this.thumbnail,
        }
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
    mounted() {
        setTimeout(() => {
            this.player.muted(false)
        }, 5000)
    },

    methods: {
    modifyLookatme() {
        this.$router.push({name: 'lookatmemodify', params: {lookatme: this.lookatme}});
    },
    onPlayerPlay(player) {
      },
      onPlayerPause(player) {
      },
      onPlayerEnded(player) {
      },
      onPlayerLoadeddata(player) {
      },
      onPlayerWaiting(player) {
      },
      onPlayerPlaying(player) {
      },
      onPlayerTimeupdate(player) {
      },
      onPlayerCanplay(player) {
      },
      onPlayerCanplaythrough(player) {
      },
      playerStateChanged(playerCurrentState) {
      },
      playerReadied(player) {
        player.currentTime(10)
      },
    getImg(file) {
      return this.fileURL + 'ServerFiles/Thumbnail/' + file;
    },
    infiniteHandler($state) {
        let c = this.lookatme.category;
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
        },
    lookatmeDetail(lookatmeId, thumbnail, video) {
      let lookatme_id = lookatmeId + "";
      console.log(video);
      this.$router.push({name: 'lookatmedetail', query: {lookatme_id: lookatme_id, thumbnail: thumbnail, video: video}});
    },
    }
}
</script>

<style>
.vjs-custom-skin {
    width: 100%;
}
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