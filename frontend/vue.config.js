module.exports = {
  publicPath: '',
  transpileDependencies: ['vuetify'],
  devServer: {
    disableHostCheck: true,
    https: true,
    open: true,
    proxy: {
      '/session': {
        target: 'https://profileglance.site:8011/',
      },
    },
    historyApiFallback: true,
    hot: true,
  },
};
