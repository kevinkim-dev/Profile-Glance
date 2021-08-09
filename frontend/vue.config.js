module.exports = {
  transpileDependencies: ['vuetify'],
  devServer: {
    disableHostCheck: true,
    https: true,
    open: true,
    proxy: {
      '/user': {
        target: 'https://profileglance.site:8877/',
      },

      '/session': {
        target: 'https://profileglance.site:8011/',
      },
    },
    historyApiFallback: true,
    hot: true,
  },
};
