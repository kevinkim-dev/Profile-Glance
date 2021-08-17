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
                <div id="video_content" style="background: #EAF5F1; border-radius: 2%;">
                    <div id="video_main">
                        <div id="content" class="my-5">
                        <h3 class="video_title">{{lookatme.title}}</h3>
                        </div>
                        <div class="d-flex justify-content-between align-items-center my-2">
                          <div class="d-flex align-items-center">
                            <div class="chat-image-box mr-2">
                              <img :src="getUserPic(lookatme.userImg)" class="chat-image" alt="profile_img">
                            </div>
                            <a @click="userPage(lookatme.userNickName)" title="클릭 시 해당 유저의 mypage로 이동합니다">{{lookatme.userNickName}}</a>
                          </div>
                          <div class="d-flex">
                            <div>조회수 {{lookatme.view}}회</div>
                            <div class="mx-1">
                              ·
                            </div>
                            <div>
                              {{ $moment(lookatme.createdAt).format("YYYY.MM.DD") }}
                            </div>
                          </div>
                        </div>
                        <div class="my-3">
                          <hr>
                          {{lookatme.content}}
                        </div>
                    </div>
                </div>
                <div v-show="isCreator" class="my-2">
                    <v-btn block text x-large class="primary-color text-white rounded-0" @click="modifyLookatme">수정</v-btn>
                </div>
            </v-col>
        </v-row>
    <br>
    <div>
      <hr>
        <div id="p-text">{{this.category}} 관련 영상 목록</div>
        <div id="lookatme-view" style="padding-top: 1rem; padding-bottom: 50px; height:auto; width: 100%;">
        <v-row>
          <v-col
            :cols="3"
            v-for="video in list"
            :key="video.lookatmeId"
            v-if="lookatme.lookatmeId != video.lookatmeId"
            @click="lookatmeDetail(video.lookatmeId, video.thumbnail, video.video)"
            >
            <v-card :loading="false" class="mx-1 lookatme" width="260" height="270px" style="padding: 10px;">
              <template slot="progress">
                <v-progress-linear
                  color="deep-purple"
                  height="10"
                  indeterminate
                ></v-progress-linear>
              </template>
              <v-img height="135" width="240" :src="getImg(video.thumbnail)"> </v-img>
              <!-- {{video.thumbnail}} -->
              <v-card-title
                ><div class="title">{{ video.title }}</div></v-card-title
              >
              <v-card-text>
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
        v-if="lookatme"
          @infinite="infiniteHandler"
          spinner="circles"
          class="mt-4"
        >
          <div slot="no-more">
            <div v-if="list.length === 1" class="no-lookatme">
            <br>
            불러올 룩앳미가 없습니다
            </div>
          </div>
        </infinite-loading>
        
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
import Vue from 'vue';
import VueMoment from 'vue-moment';
import moment from 'moment';
moment.locale('ko');
Vue.use(VueMoment, {moment});
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
    userPage(userNickName) {
			this.$router.push('/mypage/user/' + userNickName);
    },
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
        player.currentTime(0)
      },
    getUserPic(file) {
      return this.fileURL + 'ServerFiles/UserImg/' + file;
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
#content {
    display: table;
    table-layout: fixed;
    width: 100%;
}
.video_title {
  display: table-cell;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}
.video_title:hover {
    overflow: visible;
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
#video_content {
background: rgba(0, 0, 0, 0.1);
  overflow-y: scroll;
  height: 352px;
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
#p-text {
  display: inline-block;
  padding: 5px;
  background-color: #EAF5F1;
  border-radius: 5px;
  margin-left: 5px;
  /* margin-bottom: 1rem; */
}
.no-lookatme {
  margin-top: 100px;
}
</style>