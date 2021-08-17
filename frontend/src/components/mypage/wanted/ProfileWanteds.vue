<template>
  <v-simple-table
    fixed-header
    height="400px"
    class="p-2 wanted-table-box elevation-1"
  >
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">
            직무
          </th>
          <th class="text-left">
            직무 상세
          </th>
          <th class="text-left">
            경력
          </th>
          <th class="text-left">
            날짜
          </th>
          <th class="text-left">
            
          </th>
        </tr>
      </thead>
      <tbody>
        <tr class="wanted-table"
          v-for="wanted in wanteds"
          :key="wanted.recruitId"
        >
          <td>{{ wanted.job }}</td>
          <td>{{ wanted.jobDetail }}</td>
          <td>{{ wanted.career }}</td>
          <td>{{ wanted.presentationDate }}</td>
          <td class="text-right">
            <v-btn class="wanted-button"
              color="white" text
              v-if="wanted.sessionId===null"
              @click="makePresentation(wanted.recruitId, wanted.csId)"
            >
              설명회개설
            </v-btn>
            <v-btn class="wanted-button"
              color="white" text
              v-else
              @click="$router.push({name: 'userpresentation', params: { sessionid: wanted.csId, companyname: wanted.companyName }})"
            >
              설명회참여
            </v-btn>
          </td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>
<script>
import router from '@/router';
import http from '@/http.js';

export default {
  data() {
    return {
      wanteds: Array,
    }
  },
  mounted() {
    http.get('/company/companyrecruitinfo/' + this.$route.params.id) 
      .then(res => {
        this.wanteds =  res.data
      }) 
  },
  methods: {
    makePresentation (recruitId, csId) {
      const now = new Date().toISOString()
      const companyId = localStorage.getItem('id')
      const body = {companyId: companyId, recruitId: recruitId, createAt: now}
      http.post('/recruit/createRoom', body)
      .then(() => {
        router.push({name: 'companypresentation', params: { sessionid: csId, recruitid: recruitId }})
      })
      .catch(() => {
        console.log('설명회 개설에 실패했습니다.')
      })
    }
  }
}
</script>

<style>
.wanted-table-box {
  border: solid #eee 1px;
  width: 800px;
  height: 100%;
}

.wanted-table-box.theme--light.v-data-table {
  background-color: #EAF5F1;
}

.wanted-table-box.theme--light.v-data-table--fixed-header thead tr th {
  background-color: #EAF5F1;
}

.wanted-table {
  background-color: #C0DDD1;
  border: black 1px solid;
}

.wanted-button {
    width: 100px;
    background: #439474;
    margin-left: 10px
}
</style>