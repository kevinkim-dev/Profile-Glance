<template>
  <div class="d-flex justify-center align-center">
    <v-btn
      text
      class="primary-color text-white mx-3"
      @click="searchAll">
      전체보기
    </v-btn>
    <v-text-field
      style="max-width: 800px;"
      v-model="searchWord"
      append-icon="mdi-magnify"
      label="기업명을 입력해주세요"
      @keypress.enter="searchCompany"
    ></v-text-field>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  data: function () {
    return {
      searchWord: '',
      ...mapState('wanted', {
        companies: state => state.companies
      })
    }
  },
  methods: {
    searchAll: function () {
      this.$store.dispatch('wanted/setCompanies')
    },
    searchCompany: function () {
      if (!this.searchWord) {
        alert('검색어를 입력해주세요.')
      } else {
        this.$store.dispatch('wanted/searchCompany', this.searchWord)
      }
    }
  }

}
</script>

<style>

</style>