<template>
  <v-simple-table
    fixed-header
    height="350px"
    class="p-2 wanted-table-box elevation-1"
  >
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">
            이름
          </th>
          <th class="text-left">
            날짜
          </th>
          <th class="text-left">
            프로필
          </th>
          <th class="text-left">
            
          </th>
        </tr>
      </thead>
      <tbody>
        <tr class="wanted-table"
          v-for="(interview, i) in interviews"
          :key="i"
        >
          <td>{{ interviewId(interview) }}</td>
          <td>{{ interview.interviewDate }} {{ interview.interviewTime }}</td>
          <td class="text-left">
            <v-btn class="interview-button"
              color="white" text
              @click="clickProfile(interview)"
            >
              프로필 보기
            </v-btn>
          </td>
          <td class="text-right">
            <v-btn class="interview-button"
              v-if="(userType=='company')&&(interview.sessionId===null)"
              color="white" text
              @click="$router.push({ name: 'companyinterview', params: { sessionid: interview.csId, interviewee: interviewId(interview) }})"
            >
              면접장 개설
            </v-btn>
            <v-btn class="interview-button"
              v-else-if="userType=='company'"
              color="white" text
              @click="$router.push({ name: 'companyinterview', params: { sessionid: interview.csId, interviewee: interviewId(interview) }})"
            >
              면접장 입장
            </v-btn>
            <v-btn class="interview-button"
              v-else-if="interview.sessionId"
              color="white" text
              @click="$router.push({ name: 'userinterview', params: { sessionid: interview.sessionId }})"
            >
              면접장 입장
            </v-btn>
          </td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>
<script>
import http from '@/http.js';

export default {
  data() {
    return {
      interviews: Array,
      userType: String,
    }
  },
  mounted() {
    this.userType = localStorage.getItem('login_type')
    if (this.userType == 'user') {
      http.get('/user/myinterview/' + localStorage.getItem('id')) 
      .then(res => {
        console.log(res.data)
        this.interviews =  res.data
      })
    } else if (this.userType == 'company') {
      http.get('/company/companyinterviewinfo/' + this.$route.params.id)
      .then(res => {
        console.log(res.data)
        this.interviews =  res.data
      })
    }
  },
  methods: {
    clickInterview() {
      alert('면접 열기')
    },
    clickProfile(interview) {
      if (this.userType == 'user') {
        this.$router.push('/mypage/company/' + interview.companyId)
      } else if (this.userType == 'company') {
        this.$router.push('/mypage/user/' + interview.userNickname)
      }
    },
    interviewId(interview) {
      if (this.userType == 'user') {
        return interview.companyId
      } else if (this.userType == 'company') {
        return interview.userNickname
      }
    }
  },
}
</script>

<style>
.wanted-table-box {
  border: solid #eee 1px;
}

.wanted-table {
  background-color: #eee;
}

.interview-button {
    width: 100px;
    background: #439474;
    padding: 0px;
}
</style>