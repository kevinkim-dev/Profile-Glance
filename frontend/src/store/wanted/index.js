import Http from '@/http.js'
export default {
  namespaced: true,
  state: {
    companies: [],
  },
  mutations: {
    SETCOMPANIES: function (state, data) {
      state.companies = data
    }

  },
  actions: {
    setCompanies: function ({ commit }) {
      Http.get('/recruit/searchAll')
      .then((res) => {
        commit('SETCOMPANIES', res.data)
      })
      .catch((err) => {
        console.log(err)
      })
    },
    searchCompany: function ({ commit }, searchWord) {
      const URL = 'recruit/searchByCompanyName/' + searchWord
      Http.get(URL)
      .then((res) => {
        commit('SETCOMPANIES', res.data)
      })
      .catch((err) => {
        console.log(err)
      })
    },
    searchJob: function ({ commit }, searchFilter) {
      const URL = 'recruit/searchByJobName/' + searchFilter
      Http.get(URL)
      .then((res) => {
        commit('SETCOMPANIES', res.data)
      })
      .catch((err) => {
        console.log(err)
      })
    }

  }
}
