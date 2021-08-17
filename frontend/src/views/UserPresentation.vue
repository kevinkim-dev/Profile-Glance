<template>
	<div id="session-background" class="d-flex justify-content-center align-items-center">
		<div class="elevation-10 session-whole" ref="whole" >
			<div id="session-header" ref="header">
				<h1 id="session-title">{{ companyName }} 채용설명회</h1>
				<div class="d-flex flex-column pt-7">
					<Dialog
					:buttonText="'설명회 나가기'"
					:dialogTitle="'알림'"
					:dialogContent="'설명회를 나가시겠습니까?'"
					:buttonO="'네'"
					:buttonX="'아니오'"
					@clickO="exitPresentation"/>
					<div class="d-flex mt-2">
						<p v-if="totalViewers>=0"><i class="fas fa-user"></i> {{ totalViewers }}</p>
						<p class="mx-1" v-else><i class="fas fa-user"></i> 0</p>
						<p class="mx-1"><i class="fas fa-clock"></i> {{ runningTime }}</p>
					</div>
				</div>
			</div>
			<div id="session-body">
				<div id="session-video" class="d-flex justify-content-center">
					<user-video :stream-manager="mainStreamManager"/>
				</div>
				<div id="session-message">
          <div ref="chatDisplay" id="session-message-box">
            <div v-for="(chat, index) in chats" :key="index" class="chat-line">
              <div v-if="chat.userId === myUserName" class="my-comment">
                <div>
                  <!-- <span class="participant-name">[{{ chat.nickname }}] </span
                  ><span class="chat-msg">{{ chat.msg }}</span> -->
                  <div class="userInfo mb-2">
                    <div class="chat-image-box mr-2">
                      <img :src="getImg(chat)" class="chat-image" alt="profile_img">
                    </div>
                    <span class="participant-name">{{ chat.nickname }} </span>
                  </div>
                  <div class="chat-box mb-2">
                    <span class="chat-msg">{{ chat.msg }}</span>
                  </div>
                </div>
              </div>
              <div v-else class="other-comment">
                <div>
                  <!-- <span class="participant-name other">[{{ chat.nickname }}] </span
                  ><span class="chat-msg">{{ chat.msg }}</span> -->
                  <div class="userInfo mb-2">
                    <div class="chat-image-box mr-2">
                      <img :src="getImg(chat)" class="chat-image" alt="profile_img">
                    </div>
                    <span class="participant-name other">{{ chat.nickname }} </span>
                  </div>
                  <div class="chat-box mb-2">
                    <span class="chat-msg">{{ chat.msg }}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div id="session-message-send">
            <div class="msg-guide p-2 fs-4">
              내 메시지
            </div>
            <input
              v-model="sendMsg"
              type="textarea"
              id="session-message-input"
              placeholder="메세지를 입력해주세요"
              class="pt-2 pb-5 ps-2 pe-2"
              @keydown.enter="submitMsg"
            />
          </div>
        </div>
			</div>
		</div>
	</div>
</template>
<style scoped>
#session-background {
	background-color: rgb(80, 75, 75);
	/* border: solid 1px white; */
	height: 100vh;
	width: 100vw;
}

.session-whole {
	width: 95%;
	height: 95%;
	background-color: white;
	border-radius: 5px;
	overflow: hidden;
}

#session-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding-left: 80px;
	padding-right: 30px;
	padding-top: 30px;
	padding-bottom: 30px;
	border-bottom: solid rgb(151, 151, 151) 2px;
	width: 100%;
}

#session-body {
	display: flex;
	justify-content: space-between;
	position: relative;
}

#session-video {
	width: 76%;
}

#session-video2 {
	width: 100%;
}

#fullscreen-box {
	width: 30px;
	height: 30px;
}

#fullscreen-icon {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

#fullscreen-icon:hover {
	cursor: pointer;
}

#session-message {
	display: flex;
	flex-direction: column;
	justify-content: flex-end;
	position: absolute;
	right: 0px;
	height: 100%;
	width: 24%;
	background-color: #eee;
	scroll-behavior: auto;
	border-left: solid rgb(151, 151, 151) 1px;
}

#session-message-box {
	padding: 10px;
	overflow: auto;
}

#session-message-send {
	border-top: solid rgb(151, 151, 151) 1px;
	/* position: relative; */
}

#session-message-input {
	/* position: absolute; */
	/* white-space: pre-line; */
	width: 100%;
}
</style>

<script>
import http from '@/http.js';
import axios from 'axios';
import { mapGetters } from 'vuex';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/components/live/UserVideo';
import Dialog from '@/components/Dialog'
axios.defaults.headers.post['Content-Type'] = 'application/json';
const OPENVIDU_SERVER_URL = "https://profileglance.site:8011";
const OPENVIDU_SERVER_SECRET = "1234";
export default {
	name: 'UserPresentation',
	components: {
		UserVideo,
		Dialog
	},
	data () {
		return {
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			subscribers: [],
			chats: [],
			sendMsg: '',
			mySessionId: '',
			myUserName: '',
			sessionId: this.$route.params.sessionid,
      companyName: this.$route.params.companyname,
			recruitId: this.$route.params.recruitid,
			total: 0,
			startTime: undefined,
			timeGap: undefined,
		}
	},
	computed: {
		totalViewers: function () {
			return this.total
		},
		runningTime: function () {
			return this.timeGap
		},
		...mapGetters([
      'fileURL',
    ]),
	},
	created () {
    this.mySessionId = this.sessionId
    this.myUserName = localStorage.getItem('id')
		this.joinSession()
		http.get(`/room/findRoomTime/${this.mySessionId}`)
		.then((res) => {
			this.startTime = res.data
		})
		.catch((err) => {
			console.log(err)
		})
		setInterval(this.calcRunningTime, 1000)
		this.updateTotalViewers()
	},
	mounted () {
	},
  beforeDestroy () {
    this.leaveSession()
  },
	methods: {
		getImg(chat) {
        if (chat.loginType == 'user') {
            return (
                this.fileURL + 'ServerFiles/UserImg/' +
                chat.img
            )
        } else {
            return (
                this.fileURL + 'ServerFiles/companyLogo/' +
                chat.img
            )
        }
    },
		calcRunningTime () {
			const moment = require('moment')
			const now = moment()
			const startTime = moment(this.startTime)
			const hours = moment.duration(now.diff(startTime)).hours()
			const minutes = moment.duration(now.diff(startTime)).minutes()
			const seconds = moment.duration(now.diff(startTime)).seconds()
			const editedHours = hours >= 10 ? hours: '0'+hours
			const editedMinutes = minutes >= 10 ? minutes: '0'+minutes
			const editedSeconds = seconds >= 10 ? seconds: '0'+seconds
			this.timeGap = editedHours+':'+editedMinutes+':'+editedSeconds
		},
    exitPresentation () {
      this.leaveSession()
      this.$router.push({name: 'wanted'})
    },
    chat_on_scroll() {
      this.$refs.chatDisplay.scrollTop = this.$refs.chatDisplay.scrollHeight;
    },
    submitMsg () {
      if (this.sendMsg.trim() === '') return;
      const sendData = {
        userId: this.myUserName,
        nickname: this.myUserName,
        msg: this.sendMsg,
				loginType: localStorage.getItem('login_type'),
        img: localStorage.getItem('profile')
      };
      this.sendMsg = '';
      this.session
        .signal({
          data: JSON.stringify(sendData), // Any string (optional)
          to: [], // Array of Connection objects (optional. Broadcast to everyone if empty)
          type: 'my-chat', // The type of message (optional)
        })
        .then(() => {
          console.log('Message successfully sent');
        })
        .catch(error => {
          console.error(error);
        });
    },
		sendJoinSignal () {
			this.session
				.signal({
					data: '들어왔다',
					to: [],
					type: 'joinsignal'
				})
				.then(() => {
					console.log('joinsignal 보냄')
				})
				.catch((err) => {
					console.log(err)
				})
		},
		updateTotalViewers () {
			axios.get(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${this.sessionId}/connection`, {
			auth: {
				username: 'OPENVIDUAPP',
				password: OPENVIDU_SERVER_SECRET,
			},
			})
			.then((res) => {
				this.total = res.data.numberOfElements
			})
			.catch(() => {
				// console.log(err)
			})
		},
		joinSession () {
			// --- Get an OpenVidu object ---
			this.OV = new OpenVidu();
			// --- Init a session ---
			this.session = this.OV.initSession();
			// --- Specify the actions when events take place in the session ---
			// On every new Stream received...
			this.session.on('streamCreated', ({ stream }) => {
				const subscriber = this.session.subscribe(stream);
        // this.subscribers.push(subscriber);
        // this.mainStreamManager = this.subscribers[this.subscribers.length - 1];
				this.mainStreamManager = subscriber
			});
			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
				// const index = this.subscribers.indexOf(stream.streamManager, 0);
				// if (index >= 0) {
				// 	this.subscribers.splice(index, 1);
				// }
				this.mainStreamManager = undefined
				Swal.fire({ 
						icon: 'warning', // Alert 타입 
						title: '설명회가 종료되었습니다.', // Alert 제목 
						showCancelButton: false,
						showConfirmButton: true,
						showDenyButton: false,
						confirmButtonColor: '#439474',
						confirmButtonText: `나가기`,
				})
				.then((res) => {
						if(res.isConfirmed) {
								return this.exitPresentation()
						}
				})
			});

			this.session.on('signal:my-chat', event => {
        this.chats.push(JSON.parse(event.data));
        setTimeout(this.chat_on_scroll, 10);
      });
			this.session.on('signal:joinsignal', event => {
				console.log('받았다!')
				this.updateTotalViewers()
			})
			// On every asynchronous exception...
			this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});
			// --- Connect to the session with a valid user token ---
			// 'getToken' method is simulating what your server-side should do.
			// 'token' parameter should be retrieved and returned by your own backend
			this.getToken(this.mySessionId).then(token => {
				this.session
          .connect(token, { clientData: this.myUserName })
					.then(() => {
						this.sendJoinSignal()
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
			});
			// window.addEventListener('beforeunload', this.leaveSession)
		},
		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			if (this.session) this.sendJoinSignal();
			this.session.disconnect();
			this.session = undefined;
			this.mainStreamManager = undefined;
			this.subscribers = [];
			this.OV = undefined;
			// window.removeEventListener('beforeunload', this.leaveSession);
		},
		getToken (mySessionId) {
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},
		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
		createSession (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
						customSessionId: sessionId,
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(error => {
						if (error.response.status === 409) {
							resolve(sessionId);
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		},
		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
		createToken (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			});
		},
	}
}
</script>