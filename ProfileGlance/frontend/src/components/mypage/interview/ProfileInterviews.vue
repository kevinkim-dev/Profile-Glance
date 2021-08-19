<template>
  <v-simple-table
    fixed-header
    height="350px"
    class="p-2 elevation-1 interview-table-box"
  >
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left interview-table">
            이름
          </th>
          <th class="text-left interview-table">
            날짜
          </th>
          <th class="text-left interview-table" v-if="userType=='company'">
            프로필
          </th>
          <th class="text-left interview-table">
            
          </th>
        </tr>
      </thead>
      <tbody class="interview-table-body">
        <tr class="interview-table-content"
          v-for="(interview, i) in interviews"
          :key="i"
        >
          <td>{{ interviewId(interview) }}</td>
          <td>{{ interview.interviewDate }} {{ interview.interviewTime }}</td>
          <td class="text-left" v-if="userType=='company'">
            <v-btn class="interview-button"
              color="white" text
              @click="clickProfile(interview)"
            >
              프로필
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
        this.interviews =  res.data
      })
    } else if (this.userType == 'company') {
      http.get('/company/companyinterviewinfo/' + this.$route.params.id)
      .then(res => {
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
.interview-table-box {
  border: solid #eee 1px;
  width: 800px;
  height: 100%;
}

.interview-table-box.theme--light.v-data-table {
  background-color: #EAF5F1;
}


.interview-table-content {
  background-color: #C0DDD1;
  border-radius: 5px;
  border: black 1px solid;
}

.interview-table-box.theme--light.v-data-table--fixed-header thead tr th {
  background-color: #EAF5F1;
}

.interview-button {
    width: 100px;
    background: #439474;
    padding: 0px;
}

.interview-table-body {
  border-radius: 5px;
  overflow: hidden;
  border: 1px black solid;
}
</style>