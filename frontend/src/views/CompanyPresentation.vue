<template>
	<div id="session-background" class="d-flex justify-content-center align-items-center">
		<div class="elevation-10 session-whole" ref="whole2" v-if="isFullScreen">
			<div id="session-video2" class="d-flex justify-content-center">
				<user-video :stream-manager="mainStreamManager"/>
				<div class="d-flex flex-column justify-content-start" id="fullscreen-column">
					<div id="fullscreen-box">
						<img src="/images/presentation/fullscreen.png" alt="fullscreen.png" id="fullscreen-icon" @click="fullscreenOff">
					</div>
				</div>
			</div>
		</div>
		<div class="elevation-10 session-whole" ref="whole" v-else >
			<div id="session-header" ref="header">
				<h1 id="session-title">{{ mySessionId }} 설명회</h1>
				<input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="exitPresentation" value="Leave session">
			</div>
			<div id="session-body">
				<div id="session-video" class="d-flex justify-content-center">
					<user-video :stream-manager="mainStreamManager"/>
					<div class="d-flex flex-column justify-content-start" id="fullscreen-column">
						<div id="fullscreen-box">
							<img src="/images/presentation/fullscreen.png" alt="fullscreen.png" id="fullscreen-icon" @click="fullscreenOn">
						</div>
					</div>
				</div>
				<div id="session-message">
					<div ref="chatDisplay" id="session-message-box">
						<div v-for="(chat, index) in chats" :key="index" class="chat-line">
							<div v-if="chat.userId === myUserName" class="my-comment">
								<div>
									<span class="participant-name">[{{ chat.nickname }}] </span><span class="chat-msg">{{ chat.msg }}</span>
								</div>
							</div>
							<div v-else class="other-comment">
								<div>
									<span class="participant-name other">[{{ chat.nickname }}] </span
									><span class="chat-msg">{{ chat.msg }}</span>
								</div>
							</div>
						</div>
					</div>
					<div id="session-message-send">
						<div class="msg-guide p-2 fs-4" >
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
<style>
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
	padding-left: 80px;
	padding-right: 80px;
	padding-top: 30px;
	padding-bottom: 30px;
	border-bottom: solid rgb(151, 151, 151) 2px;
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

.msg-guide {
	/* border-bottom: solid rgb(151, 151, 151) 1px; */
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
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/components/live/UserVideo';
axios.defaults.headers.post['Content-Type'] = 'application/json';
const OPENVIDU_SERVER_URL = "https://profileglance.site:8011";
const OPENVIDU_SERVER_SECRET = "1234";
export default {
	name: 'CompanyPresentation',
	components: {
		UserVideo,
	},
	data () {
		return {
			screenSize: String,
			isFullScreen: false,
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			subscribers: [],
			chats: [],
			sendMsg: '',
			mySessionId: '',
			myUserName: '',
			sessionId: this.$route.params.sessionid,
			recruitId: this.$route.params.recruitid,
			width: Number,
			originalSize: Object,
			fullSize: Object,
		}
	},
	created () {
    this.mySessionId = this.sessionId
    this.myUserName = localStorage.getItem('id')
		const body = {companyId: this.myUserName, recruitId: this.recruitId}
		console.log(body)
		http.post('/recruit/createRoom', body)
		.then((res) => {
			console.log(res)
		})
	},
	mounted() {
		this.originalSize = {
			'height': this.$refs.whole.clientHeight - this.$refs.header.clientHeight,
			'width': (this.$refs.whole.clientHeight - this.$refs.header.clientHeight)*16/9
		}
		this.fullSize = {
			'height': this.$refs.whole.clientHeight,
			'width': this.$refs.whole.clientHeight*16/9
		}
		this.screenSize = this.originalSize.width + 'x' + this.originalSize.height
		this.joinSession()
	},
  beforeDestroy () {
		this.leaveSession()
  },
	methods: {
		exitPresentation () {
			localStorage.removeItem('isSession')
      this.leaveSession()
      this.$router.go(-1)
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
		joinSession () {
			// --- Get an OpenVidu object ---
			this.OV = new OpenVidu();
			// --- Init a session ---
			this.session = this.OV.initSession();
			// --- Specify the actions when events take place in the session ---
			// On every new Stream received...
			this.session.on('signal:my-chat', event => {
				this.chats.push(JSON.parse(event.data));
        setTimeout(this.chat_on_scroll, 10);
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
								resolution: this.screenSize,  // The resolution of your video
								frameRate: 30,			// The frame rate of your video
								insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
								mirror: false       	// Whether to mirror your local video or not
							});
							this.mainStreamManager = publisher;
							// --- Publish your stream ---
							this.session.publish(this.mainStreamManager);
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
			});
			window.addEventListener('beforeunload', this.leaveSession)
		},
		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			const body = {companyId: this.myUserName, sessionId: this.sessionId, recruitId: this.recruitId}
			http.post('/room/deleteRecruitSessionId', body)
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
		fullscreenOn() {
			this.screenSize = this.fullSize.width + 'x' + this.fullSize.height
			this.joinSession()
			setTimeout(this.setFullScreen, 400)
		},
		fullscreenOff() {
			this.screenSize = this.originalSize.width + 'x' + this.originalSize.height
			this.joinSession()
			setTimeout(this.unsetFullScreen, 400)
		},
		setFullScreen() {
			this.isFullScreen = true
		},
		unsetFullScreen() {
			this.isFullScreen = false
		}
	}
}
</script>