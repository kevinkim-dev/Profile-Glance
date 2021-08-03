export default {
  namespaced: true,
  state: {
    profileType: 1,
    profileId: 1,
    userInfo: {
      userEmail: 'test6@test.com',
      userName: 'test6',
      userNickname: 'test6',
      userBirth: '1995-10-30',
      userPhone: '010-1010-1010',
      major1: '전자',
      major2: '기계',
      companyLike: 0,
      portfolio1: 'git',
      portfolio2: 'git',
    }
  },
  mutations: {
    EDIT(state, editForm) {
      state.userInfo.userPhone = editForm.userPhone
      state.userInfo.major1 = editForm.major1
      state.userInfo.major2 = editForm.major2
      state.userInfo.portfolio1 = editForm.portfolio1
      state.userInfo.portfolio2 = editForm.portfolio2
    }
  },
  actions: {
    edit({commit}, editForm) {
      commit('EDIT', editForm)
    }
  }
}
