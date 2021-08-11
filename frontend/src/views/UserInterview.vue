<template>
	<div id="main-container" class="container">
		<div id="session" v-if="session">
			<div id="session-header">
				<h1 id="session-title">화상면접장</h1>
				<input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="exitPresentation" value="Leave session">
			</div>
      <!-- <h3>면접대상자</h3>
			<div id="main-video" class="col-md-6">
				<user-video :stream-manager="mainStreamManager"/>
			</div> -->
			<div id="video-container" class="col-md-6">
				<h3>면접관</h3>
        <div class="d-flex">
          <user-video v-for="pub in publishers" :key="pub.stream.connection.connectionId+'2'" :stream-manager="pub"/>
        </div>
        <h3>나</h3>
				<user-video :stream-manager="publisher" @click.native="updateMainVideoStreamManager(publisher)"/>
        <!-- <p>subscribers</p>
        <user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.native="updateMainVideoStreamManager(sub)"/> -->
			</div>
			<div class="chat-box">
				<div ref="chatDisplay" class="chat-display">
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
				<br>
				<div class="msg-wrapper">
					<div class="msg-guide">
						<!-- <img :src="getUserInfo.profileImage" class="user-profile" /> -->
						{{ myUserName.nickname }}
					</div>
					<input
						v-model="sendMsg"
						type="text"
						class="msg-input"
						placeholder="메세지를 입력해주세요"
						@keydown.enter="submitMsg"
					/>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/components/live/UserVideo';
axios.defaults.headers.post['Content-Type'] = 'application/json';
const OPENVIDU_SERVER_URL = "https://profileglance.site:8011";
const OPENVIDU_SERVER_SECRET = "1234";
export default {
	name: 'UserInterview',
	components: {
		UserVideo,
	},
	data () {
		return {
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
      sessionId: this.$route.params.sessionid,
      publisher: undefined,
			subscribers: [],
      publishers: [],
			chats: [],
			sendMsg: '',
			mySessionId: '',
			myUserName: '',
		}
	},
	created () {
    this.mySessionId = this.sessionId
    this.myUserName = localStorage.getItem('id')
		this.joinSession()
	},
  beforeDestroy () {
    this.leaveSession()
  },
  computed: {
    changedPublishers: function () {
      return publishers
    }
  },
	methods: {
    exitPresentation () {
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
      this.session.on('streamCreated', ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        console.log(subscriber)
        this.subscribers.push(subscriber);
        this.publishers.push(subscriber)
        this.mainStreamManager = this.subscribers[this.subscribers.length - 1];
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
			// On every asynchronous exception...
			this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});
			// --- Connect to the session with a valid user token ---
			// 'getToken' method is simulating what your server-side should do.
			// 'token' parameter should be retrieved and returned by your own backend
			this.getToken(this.mySessionId).then(token => {
				this.session
          .connect(token, { clientData: this.myUserName, type: 'user' })
					.then(() => {
						let publisher = this.OV.initPublisher(undefined, {
								audioSource: undefined, // The source of audio. If undefined default microphone
								videoSource: undefined, // The source of video. If undefined default webcam
								publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
								publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
								resolution: '640x360',  // The resolution of your video
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
	}
}
</script>