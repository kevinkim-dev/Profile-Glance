export default {
  namespaced: true,
  state: {
    profileType: 1,
    profileId: 1,
    liked: false,
  },
  mutations: {
    CLICK_LIKE() {
      this.liked = !this.liked
    }
  },
  actions: {
    clickLike({commit}) {
      commit('CLICK_LIKE')
      //axios통신(백앤드 구현 후 구현)
    },
  }
}
