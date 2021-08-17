<template>
  <div>
    <v-row class="d-flex" style="max-width:60vw; margin:auto;">
      <v-col cols="2">
      </v-col>
      <v-col class="d-flex align-center" cols="2">
        <!-- <span
          style="color: #439474; cursor: pointer;"
          class="mx-4 font-weight-bold"
          @click="searchAll">
          전체보기
        </span> -->
        <v-select
          label="모집 직무"
          :items="jobFilter"
          v-model="searchFilter"
          @input="searchJob"
          :disabled="filteringDisabled"
        >
        </v-select>
      </v-col>
      <v-col class="d-flex" cols="5">
        <v-text-field
          style="max-width: 800px;"
          v-model="searchWord"
          append-icon="mdi-magnify"
          :label="searchBarLabel"
          @keypress.enter="searchCompany"
          :disabled="searchBarDisabled"
        ></v-text-field>
      </v-col>
      <v-col cols="1">
      </v-col>
      <v-col class="d-flex" cols="2">
      <v-btn
          text
          large
          class="m-3 primary-color text-white rounded-0"
          v-if="isCompany"
          @click="$router.push({ name: 'wantedregist' })">
          채용 공고 등록</v-btn>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  data: function () {
    return {
      // 회사명 검색
      searchWord: '',
      ...mapState('wanted', {
        companies: state => state.companies
      }),
      // 직무 필터링
      searchFilter: '전체보기',
      jobFilter: [
        '전체보기',
        'IT',
        '마케팅',
        '영업',
        '경영지원',
        '디자인',
        '서비스',
        '전문직',
        '의류',
        '생산제조',
        '건설',
        '유통무역',
        '미디어',
        '교육',
        '특수계층/공공',
        '연구직',
      ],
    }
  },
  computed: {
    // 검색창 disable 여부 (필터링 했다면 true)
    searchBarDisabled: function () {
      if (this.searchFilter != "전체보기") {
        return true
      } else {
        return false
      }
    },
    filteringDisabled: function () {
      if (this.searchWord) {
        return true
      } else {
        return false
      }
    },
    searchBarLabel: function () {
      if (this.searchFilter != "전체보기") {
        return '검색창을 활성화하려면 전체보기를 누르세요'
      } else {
        return '기업명을 입력해주세요'
      }
    },
    isCompany: function () {
      if (localStorage.getItem('login_type') === 'company') {
        return true
      } else {
        return false
      }
    }
  },
  methods: {
    searchAll: function () {
      this.searchWord = ''
      this.searchFilter = '전체보기'
      this.$store.dispatch('wanted/setCompanies')
    },
    searchCompany: function () {
      if (!this.searchWord) {
        Swal.fire({ 
          icon: 'warning', // Alert 타입 
          title: '검색어를 입력해주세요.', // Alert 제목 
          text: '검색어를 입력해야 검색이 가능합니다.', // Alert 내용 
        });
        // alert('검색어를 입력해주세요.')
      } else {
        this.$store.dispatch('wanted/searchCompany', this.searchWord)
      }
    },
    searchJob: function () {
      if (this.searchFilter === '전체보기')
        this.searchAll();
      else
        this.$store.dispatch('wanted/searchJob', this.searchFilter)
    }
  }

}
</script>

<style>
</style>