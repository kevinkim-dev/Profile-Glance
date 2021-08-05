export default {
  namespaced: true,
  state: {
    profileType: Number,
    profileId: 1,
    data: {
      userData: {
        userEmail: '',
        userName: '',
        userNickname: '',
        userBirth: '',
        major1: '',
        major2: '',
        countLike: 0,
        countVideo: 0,
        portfolio1: '',
        portfolio2: '',
        userImg: '',
      },
      companyData: {
        companyId: '',
        companyEmail: '',
        companyName: '',
        companyPhone: '',
        companyImg: '',
      }
    },
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
      state.data.userData.userPhone = editForm.userPhone
      state.data.userData.major1 = editForm.major1
      state.data.userData.major2 = editForm.major2
      state.data.userData.portfolio1 = editForm.portfolio1
      state.data.userData.portfolio2 = editForm.portfolio2
    },
    SHOW_MYPAGE(state, mystate) {
      state.data = mystate.data
      state.profileType = mystate.userType
    }
  },
  actions: {
    edit({commit}, editForm) {
      commit('EDIT', editForm)
    },
    showMypage({commit}, mystate) {
      commit('SHOW_MYPAGE', mystate)
    }
  }
}
