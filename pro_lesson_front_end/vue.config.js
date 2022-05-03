const { defineConfig } = require('@vue/cli-service')
publicPath: process.env.NODE_ENV === 'production'
module.exports = defineConfig({
  transpileDependencies: true,
  publicPath: process.env.NODE_ENV === 'production' ? '/ProLesson/' : '/'
})


