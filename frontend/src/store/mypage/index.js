import Http from '../../http';

export default {
  namespaced: true,
  state: {
    profileType: String,
    userData: Object,
    companyData: Object,
    userInterviews: [
      {
        companyName: '네이버',
        interviewDate: '2020.08.01',
        interviewTime: '16:00',
      },
      {
        companyName: '쿠팡',
        interviewDate: '2020.08.02',
        interviewTime: '14:00',
      },
      {
        companyName: '카카오',
        interviewDate: '2020.08.03',
        interviewTime: '17:00',
      }
    ]
  },
  mutations: {
    EDIT(state, editForm) {
      state.userData.userPhone = editForm.userPhone
      state.userData.major1 = editForm.major1
      state.userData.major2 = editForm.major2
      state.userData.portfolio1 = editForm.portfolio1
      state.userData.portfolio2 = editForm.portfolio2
    },
    SET_USER_DATA(state, userData) {
      console.log('user')
      state.profileType = 'user'
      state.userData = userData
    },
    SET_COMPANY_DATA(state, companyData) {
      console.log('company')
      state.profileType = 'company'
      state.companyData = companyData
    },
    SET_ADMIN_DATA(state) {
      console.log('admin')
      state.profileType = 'admin'
    }
  },
  actions: {
    edit({commit}, editForm) {
      commit('EDIT', editForm)
    },
    getUserData({commit}, info) {
      if (info.profileType == 'user') {
        Http.get('/user/myinfo/nickname/' + info.id)
        .then(res => {
          console.log(res.data)
          commit('SET_USER_DATA', res.data)
        })
        .catch(err => {
          console.log(err)
        })
      } else if (info.profileType == 'company') {
        Http.get('/company/companyinfo/' + info.id)
        .then(res => {
          commit('SET_COMPANY_DATA', res.data)
        })
        .catch(err => {
          console.log(err)
        })
      } else if (info.profileType == 'admin') {
        commit('SET_ADMIN_DATA')
      }
    }
  }
}
