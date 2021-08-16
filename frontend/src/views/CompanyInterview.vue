<template>
	<div id="session-background" class="d-flex justify-content-center align-items-center">
		<div class="elevation-10 session-whole" v-if="session">
			<div id="session-header">
				<div>
					<img class="header-logo-letter" src="/images/icons/logo_letter.png" alt="IMG-LOGO">
				</div>
				<Dialog
				:buttonText="'면접장 퇴장'"
				:dialogTitle="'알림'"
				:dialogContent="'면접장을 퇴장하시겠습니까?'"
				:buttonO="'네'"
				:buttonX="'아니오'"
				@clickO="exitInterview"/>
			</div>
			<div id="session-body">
				<div id="session-video" ref="size" class="d-inline-flex row">
					<div id="session-video" ref="size" class="container">
          <div id="video-container" class="d-flex col-md-12">
            <user-video
              :stream-manager="publisher"
              @click.native="updateMainVideoStreamManager(publisher)"
            />
            <user-video
              v-for="pub in publishers"
              :key="pub.stream.connection.connectionId + '2'"
              :stream-manager="pub"
            />
          </div>
        </div>
				</div>
				<div id="session-message">
					<div id="session-message-header" class="elevation-2">
						{{this.sessionId}} 면접
					</div>
					<div ref="chatDisplay" id="session-message-box">
						<div v-for="(chat, index) in chats" :key="index" class="chat-line">
							<div v-if="chat.userId === myUserName" class="my-comment">
								<div>
									<div class="d-flex justify-content-end">
										<div class="userInfo mb-2">
											<div class="chat-image-box mr-2">
												<img :src="getImg(chat)" class="chat-image" alt="profile_img">
											</div>
											<span class="participant-name">{{ chat.nickname }} </span>
										</div>
									</div>
									<div class="my-chat-box mb-2">
										<span class="chat-msg">{{ chat.msg }}</span>
									</div>
								</div>
							</div>
							<div v-else class="other-comment">
								<div>
									<div class="d-flex justify-content-between">
										<div class="userInfo mb-2">
											<div class="chat-image-box mr-2">
												<img :src="getImg(chat)" class="chat-image" alt="profile_img">
											</div>
											<span class="participant-name other">{{ chat.nickname }} </span>
										</div>
										<div class="icon-box elevation-1" @click="submitQuestion(chat)"><i class="far fa-comment"></i></div>
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
		<!-- <div id="question-box" v-if="this.question.msg">
			<div id="question-header">
				<div>{{question.nickname}}님의 질문</div>
				<div @click="deleteQuestion">X</div>
			</div>
			<div id="question-content">{{question.msg}}</div>
		</div> -->
		<v-btn @click="isQuestion = true" class="d-none"></v-btn>
    <v-snackbar v-model="isQuestion" :vertical="vertical" top light class="m-t-50" :timeout="timeout"
		>
      <div id="question-header">
				<div id="question-nickname">{{question.nickname}}님의 질문</div>
				<v-btn color="indigo" text v-bind="attrs" @click="isQuestion = false" class="">
					닫기
				</v-btn>
			</div>
			<div id="question-content">{{question.msg}}</div>
    </v-snackbar>
	</div>
</template>
<style scoped>	
.header-logo-letter {
    height: 40px;
    width: 300px; 
    object-fit: cover;
    background: none;
}

#header-company-name {
	font-size: 2rem;
}

.icon-box {
	display: flex;
	flex-direction: column;
	justify-content: center;
	text-align: center;
	color: rgb(49, 49, 49);
	height: 30px;
	width: 30px;
	margin-top: 5px;
	margin-right: 10px;
	/* padding-left: 5px; */
	background: white;
	border: outset;
	border-radius: 5px;
}

#question-header {
	display: flex;
	justify-content: space-between;
	border-bottom: rgb(82, 82, 82) solid 1px;
	padding-left: 10px;
	padding-right: 0px;
	padding-bottom: 10px;
}

#question-nickname {
	font-size: 20px;
	padding-top: 8px;
}

#question-content {
	padding-top: 20px;
	padding-left: 10px;
	padding-right: 10px;
}

.chat-box {
  padding: 5px;
  background-color: #eee;
	border: 1px solid rgb(197, 197, 197);
  /* background-color: #C0DDD1; */
  border-radius: 5px;
}

.my-chat-box {
  padding: 5px;
  background-color: #439474;
	color: white;
	border: 1px solid rgb(197, 197, 197);
  /* background-color: #C0DDD1; */
  border-radius: 5px;
}

.userInfo {
  height: 35px;
  display: flex;
  align-items: center;
}

.participant-name {
  height: 30px;
  line-height: 25px;
}

.chat-image-box {
    height: 30px;
    width: 30px;
    border-radius: 70%;
    overflow: hidden;
}

.chat-image {
    width: 100%;
    height: 100%;   
    object-fit: cover;
}

#session-background {
  background-color: rgb(199, 199, 199);
  /* border: solid 1px white; */
  height: 100vh;
  width: 100vw;
}

.session-whole {
  width: 95%;
  height: 95%;
  background-color: white;
  border-radius: 10px;
  overflow: hidden;
	border: 1px solid rgb(151, 151, 151);

}

#session-header {
	display: flex;
  justify-content: space-between;
  height: 15%;
  padding-left: 30px;
  padding-right: 80px;
  padding-top: 30px;
  padding-bottom: 30px;
  border-bottom: solid rgb(151, 151, 151) 2px;
	background: linear-gradient( to right, rgb(197, 204, 206), #C0DDD1 );
}

#session-body {
  height: 85%;
  display: flex;
  /* justify-content: space-between; */
  position: relative;
	background: rgb(155, 155, 155);
}

#session-video {
  width: 70vw;
  height: 70vh;
  position: absolute;
  margin-left: 1vw;
  margin-bottom: 0px;
  margin-right: 2vw;
  margin-top: 0px;
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
  justify-content: space-between;
  position: absolute;
  right: 0px;
  height: 100%;
  width: 24%;
  /* background-color: #eee; */
  background-color: #C0DDD1;
  border-left: solid rgb(151, 151, 151) 1px;
}

#session-message-header {
	text-align: center;
	padding: 10px;
	font-size: 18px;
	border-bottom: 1px rgb(151, 151, 151) solid;
	background: rgb(223, 223, 223);
	border-radius: 5px;
	margin: 15px;
}

#session-message-box {
	scroll-behavior: auto;
	overflow: auto;
	padding: 10px;
}


#session-message-send {
	border-top: solid rgb(151, 151, 151) 1px;
}

#session-message-input {
  width: 100%;
}
video {
  width: 100%;
  height: 70%;
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
	name: 'CompanyInterview',
	components: {
		UserVideo,
		Dialog
	},
	data () {
		return {
			timeout: 3000,
			isQuestion: false,
			OV: undefined,
			session: undefined,
      sessionId: this.$route.params.sessionid,
			interviewee: this.$route.params.interviewee,
      publisher: undefined,
			subscribers: [],
      publishers: [],
			chats: [],
			question: Object,
			sendMsg: '',
			mySessionId: '',
			myUserName: '',
			isHost: false,
			size: Object,
			videoSize: String
		}
	},
	computed: {
    changedPublishers: function () {
      return publishers
    },
		...mapGetters([
      'fileURL',
    ]),
  },
	created () {
    this.mySessionId = this.sessionId
    this.myUserName = localStorage.getItem('id')
		// 면접방 존재하는지 확인
		const body = {csId: this.sessionId, userNickname: this.interviewee}
		http.post('/interview/checkCSID', body)
		.then((res) => {
			console.log('두두둥장')
			// 없으면(202면) 넣어주기
			const statusCode = res.data.statusCode
			console.log(statusCode)
			if (statusCode === 202) {
				console.log('여기')
				this.isHost = true
				var now = new Date().toISOString()
				this.startTime = now
				const body = {companyId: this.myUserName, userNickname: this.interviewee, createAt: now}
				http.post('/interview/createroom', body)
				.then((res) => {
					console.log(res)
				})
				.catch((err) => {
					console.log(err)
				})
			}
		})
	},
	mounted() {
		// this.size = {
		// 	'height': this.$refs.size.clientHeight / 2,
		// 	'width': this.$refs.size.clientWidth / 2
		// }
		// this.videoSize = this.size.width + 'x' + this.size.height
		this.joinSession()
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
		removeSession () {
			axios.delete(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${this.sessionId}`, {
			auth: {
				username: 'OPENVIDUAPP',
				password: OPENVIDU_SERVER_SECRET,
			},
			})
			.then((res) => {
				console.log(res)
			})
			.catch((err) => {
				console.log(err)
			})
		},
    exitInterview () {
			console.log('ishost확인 전')
			console.log(this.isHost)
      this.leaveSession()
			if (this.isHost) {
				console.log('확인 후')
				const body = {companyId: this.myUserName, sessionId: this.sessionId}
				console.log(body)
				http.post('/room/deleteInterview', body)
				.then((res) => {
					this.removeSession()
				})
				.catch((err) => {
					console.log(err)
				})
			}
      this.$router.go(-1)
    },
    chat_on_scroll() {
      this.$refs.chatDisplay.scrollTop = this.$refs.chatDisplay.scrollHeight;
    },
    submitMsg() {
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
        .catch((error) => {
          console.error(error);
        });
    },
    submitQuestion(msg) {
      const sendData = {
        userId: this.myUserName,
        nickname: msg.nickname,
        msg: msg.msg,
        img: msg.img
      };
      this.session
        .signal({
          data: JSON.stringify(sendData), // Any string (optional)
          to: [], // Array of Connection objects (optional. Broadcast to everyone if empty)
          type: 'question', // The type of message (optional)
        })
        .then(() => {
          console.log('Message successfully sent');
        })
        .catch((error) => {
          console.error(error);
        });
    },
		deleteQuestion() {
			this.question = Object
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
        this.publishers.push(subscriber)
			});
			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.publishers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.publishers.splice(index, 1);
				}
			});
			this.session.on('signal:my-chat', event => {
        this.chats.push(JSON.parse(event.data));
        setTimeout(this.chat_on_scroll, 10);
      });
			this.session.on('signal:question', event => {
        this.question = JSON.parse(event.data);
				this.isQuestion = true
      });
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
						let publisher = this.OV.initPublisher(undefined, {
								audioSource: undefined, // The source of audio. If undefined default microphone
								videoSource: undefined, // The source of video. If undefined default webcam
								publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
								publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
								resolution: '1280x720',  // The resolution of your video
								frameRate: 30,			// The frame rate of your video
								insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
								mirror: false,       	// Whether to mirror your local video or not
              });
							this.publisher = publisher;
							// --- Publish your stream ---
							this.session.publish(this.publisher);
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
			});
			window.addEventListener('beforeunload', this.leaveSession)
		},
		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			if (this.session) this.session.disconnect();
			this.session = undefined;
			this.mainStreamManager = undefined;
			this.subscribers = [];
			this.OV = undefined;
			window.removeEventListener('beforeunload', this.leaveSession);
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
    updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},
	}
}
</script>