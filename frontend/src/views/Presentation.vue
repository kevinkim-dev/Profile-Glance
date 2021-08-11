<template>
	<div id="main-container" class="container">
		<div id="join" v-if="!session">
			<!-- <div id="img-div"><img src="resources/images/openvidu_grey_bg_transp_cropped.png" /></div> -->
			<div id="join-dialog" class="jumbotron vertical-center">
				<h1>Join a video session</h1>
				<div class="form-group">
					<!-- <p>
						<label>Participant</label>
						<input v-model="myUserName" class="form-control" type="text" required>
					</p> -->
					<p>
						<label>Session</label>
						<input v-model="mySessionId" class="form-control" type="text">
					</p>
					<p class="text-center">
						<button class="btn btn-lg btn-success" @click="joinSession('sessionA')">join session</button>
					</p>
          <!-- <p class="text-center">
						<button class="btn btn-lg btn-success" @click="createSession(mySessionId)">create session</button>
					</p> -->
          <!-- <p class="text-center">
						<button class="btn btn-lg btn-success" @click="getAllSessions()">subscriber로 참여</button>
					</p> -->
					<p class="text-center">
						<button class="btn btn-lg btn-success" @click="createPresentation('line6')">설명회개설</button>
					</p>
					<p class="text-center">
						<button class="btn btn-lg btn-success" @click="joinPresentation('line6')">LIVE-ON</button>
					</p>
					<p class="text-center">
						<button class="btn btn-lg btn-success" @click="getSession('line6')">세션정보 가져오기</button>
					</p>
				</div>
        <p>전체 세션 목록</p>
        <div v-for="(session, idx) in sessions.content" :key="idx+'sessions'">
          {{ session.sessionId }}
        </div>
			</div>
		</div>

		<div id="session" v-if="session">
			<div id="session-header">
				<h1 id="session-title">{{ mySessionId }}</h1>
				<input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="Leave session">
			</div>
			<!-- <div id="main-video" class="col-md-6">
				<user-video :stream-manager="mainStreamManager"/>
			</div> -->
			<div id="video-container" class="col-md-6">
        <p>publisher</p>
				<user-video :stream-manager="publisher" @click.native="updateMainVideoStreamManager(publisher)"/>
				<p>subscribers</p>
        <user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.native="updateMainVideoStreamManager(sub)"/>
				{{ subscribers }}
			</div>
			<div class="chat-box">
				<div ref="chatDisplay" class="chat-display">
					<div v-for="(chat, index) in chats" :key="index" class="chat-line">
						<div v-if="chat.userId === getUserInfo.id" class="my-comment">
							<div>
								<span class="participant-name">{{ chat.nickname }} </span><span class="chat-msg">{{ chat.msg }}</span>
							</div>
						</div>
						<div v-else class="other-comment">
							<div>
								<span class="participant-name other">{{ chat.nickname }} </span
								><span class="chat-msg">{{ chat.msg }}</span>
							</div>
						</div>
					</div>
				</div>
				<div class="msg-wrapper">
					<div class="msg-guide">
						<!-- <img :src="getUserInfo.profileImage" class="user-profile" /> -->
						{{ getUserInfo.nickname }}
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
	name: 'Presentation',

	components: {
		UserVideo,
	},

	data () {
		return {
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],
			chats: [],
			sendMsg: '',
			getUserInfo: {
				id: localStorage.getItem('id'),
				nickname: localStorage.getItem('id')
			},
			mySessionId: localStorage.getItem('id'),
			// myUserName: 'Participant' + Math.floor(Math.random() * 100),
      sessions: [],
		}
	},
	computed: {
		myUserName: function () {
			return localStorage.getItem('id')
		}
	},
	methods: {
		// 채용설명회 개설
    createPresentation (sessionId) {
			this.OV = new OpenVidu();
			this.session = this.OV.initSession();
			console.log(this.session)

			// On every new Stream received...
			this.session.on('streamCreated', ({ stream }) => {
				const subscriber = this.session.subscribe(stream);
				this.subscribers.push(subscriber);
			});

			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
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

			// 1. 기업회원이 갖고있는 세션아이디(sessionId)에 해당하는 세션 생성
			this.createSession(sessionId)
			.then((data) => {
				// 2. 만들어진 세션에 해당하는 토큰 생성
				this.createToken(data, 'PUBLISHER')
				// 3. 만들어진 토큰으로 세션 연결
				.then((token) => {
					this.session.connect(token, { clientData: this.getUserInfo.id })
					.then(() => {
						// 4. publisher로 들어감
						let publisher = this.OV.initPublisher(undefined, {
							audioSource: undefined, // The source of audio. If undefined default microphone
							videoSource: undefined, // The source of video. If undefined default webcam
							publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
							publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
							resolution: '640x480',  // The resolution of your video
							frameRate: 30,			// The frame rate of your video
							insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
							mirror: false       	// Whether to mirror your local video or not
						});
						this.mainStreamManager = publisher;
						this.publisher = publisher;

						this.session.publish(this.publisher);
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
				}
				)}
			)
		},
		joinPresentation (sessionId) {
			this.OV = new OpenVidu();
			this.session = this.OV.initSession();

			// On every new Stream received...
			this.session.on('streamCreated', ({ stream }) => {
				const subscriber = this.session.subscribe(stream);
				this.subscribers.push(subscriber);
			});

			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
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

			this.getSession(sessionId)
			.then((data) => {
				console.log('1')
				this.createToken(data, 'SUBSCRIBER')
				.then((token) => {
					console.log('2')
					this.session.connect(token, { clientData: this.getUserInfo.id })
					// console.log('2.5')
					.then(() => {
						console.log('3')
						this.session.on('streamCreated', ({ stream }) => {
						const subscriber = this.session.subscribe(stream);
						this.subscribers.push(subscriber);
					});
					})
				})
			})
		},
		// 모든 세션 목록 가져오는 함수
    getAllSessions () {
      axios
					.get(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
      .then((res) => {
        this.sessions = res.data
      })
      .catch((err) => {
        console.log(err)
      })
    },
		// 기존의 joinSession
		joinSession (sessionId) {
			// --- Get an OpenVidu object ---
			this.OV = new OpenVidu();

			// --- Init a session ---
			this.session = this.OV.initSession();
			console.log('init a session')
			console.log(this.session)

			// --- Specify the actions when events take place in the session ---

			// On every new Stream received...
			this.session.on('streamCreated', ({ stream }) => {
				const subscriber = this.session.subscribe(stream);
				this.subscribers.push(subscriber);
			});

			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
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
			// getToken: 세션생성 -> 세션에 해당하는 토큰까지 생성 -> 그 토큰으로 세션 연결!
			this.getToken(sessionId).then(token => {
				this.session.connect(token, { clientData: this.myUserName })
					.then(() => {

						// --- Get your own camera stream with the desired properties ---

						let publisher = this.OV.initPublisher(undefined, {
							audioSource: undefined, // The source of audio. If undefined default microphone
							videoSource: undefined, // The source of video. If undefined default webcam
							publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
							publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
							resolution: '640x480',  // The resolution of your video
							frameRate: 30,			// The frame rate of your video
							insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
							mirror: false       	// Whether to mirror your local video or not
						});

						this.mainStreamManager = publisher;
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
			this.publisher = undefined;
			this.subscribers = [];
			this.OV = undefined;

			window.removeEventListener('beforeunload', this.leaveSession);
		},

		updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},

		/**
		 * --------------------------
		 * SERVER-SIDE RESPONSIBILITY
		 * --------------------------
		 * These methods retrieve the mandatory user token from OpenVidu Server.
		 * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
		 * the API REST, openvidu-java-client or openvidu-node-client):
		 *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
		 *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
		 *   3) The Connection.token must be consumed in Session.connect() method
		 */
		// sessionid로 session 생성 -> 그 세션 아이디로 토큰 create
		getToken (mySessionId) {
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},

		getSession (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.get(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}`, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
      .then((res) => {
				console.log('getSession 성공')
        console.log(typeof res)
				console.log(res)
				console.log(typeof res.data)
				console.log(res.data)
				resolve(res.data)
      })
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
			})
		},
		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
    // sessionid로 session 생성
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
					.then(response => {
            // console.log(response)
            response.data})
					.then(data => {
            resolve(data.id)
            console.log(data.id)})
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
		// sessionid에 해당하는 세션의 토큰 생성
		createToken (sessionId, role) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {
						role: role,
					}, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => {
						console.log('토큰')
						console.log(data)
						resolve(data.token)
					})
					.catch(error => reject(error.response));
			});
		},
		// 채팅 기능
		submitMsg () {
      if (this.sendMsg.trim() === '') return;
      const sendData = {
        userId: this.getUserInfo.id,
        nickname: this.getUserInfo.nickname,
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
	},
  created () {
    this.getAllSessions()
  }
}
</script>
