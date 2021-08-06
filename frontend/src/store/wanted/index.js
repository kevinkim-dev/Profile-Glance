import Http from '@/http.js'
export default {
  namespaced: true,
  state: {
    companies: [],
  },
  mutations: {
    SETCOMPANIES: function (state, data) {
      console.log(data)
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
    }

  }
}
