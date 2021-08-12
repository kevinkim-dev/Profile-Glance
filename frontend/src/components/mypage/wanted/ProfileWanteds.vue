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
        {{ wanted }}
          <td>{{ wanted.job }}</td>
          <td>{{ wanted.jobDetail }}</td>
          <td>{{ wanted.career }}</td>
          <td>{{ wanted.presentationDate }}</td>
          <td class="text-right">
            <v-btn class="wanted-button"
              color="white" text
              v-if="wanted.sessionId===null"
              @click="$router.push({name: 'companypresentation', params: { sessionid: wanted.csId }})"
            >
              설명회개설
            </v-btn>
            <v-btn class="wanted-button"
              color="white" text
              v-else
              @click="$router.push({name: 'userpresentation', params: { sessionid: wanted.csId }})"
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
        console.log(res.data)
        this.wanteds =  res.data
      }) 
  },
  methods: {
    clickWanted() {
      alert('설명회 열기')
    }
  }
}
</script>

<style>
.wanted-table-box {
  border: solid #eee 1px;
}

.wanted-table {
  background-color: #eee;
}

.wanted-button {
    width: 100px;
    background: #439474;
    margin-left: 10px
}
</style>