const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false, //关闭语法检查
  pages: {
    index: {
      // page 的入口
      entry: 'src/main.js'
    }
  },
  /* 
    方式1
    开启代理服务器，解决跨域问题
    会现在自己身上找资源，找不到在去服务器找
   */
  /* devServer: {
    proxy: 'http://cookie2.aaa.com:8899'
  }, */
  /* 方式2 */
  devServer: {
    proxy: {
      '/server1': {
        target: 'http://cookie1.aaa.com:8899',
        pathRewrite:{'^/server1':''},
        //ws: true, //websocket使用
        /*
          changeOrigin: true
              x-forwarded-host : 192.168.0.109:8080
              x-forwarded-proto : http
              x-forwarded-port : 8080
              x-forwarded-for : 192.168.0.109
              accept-language : zh-CN,zh;q=0.9
              accept-encoding : gzip, deflate
              referer : http://192.168.0.109:8080/index.html
              user-agent : Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36
              accept : application/json, text/plain
              connection : close
              host : cookie2.aaa.com:8899
          changeOrigin: false
              x-forwarded-host : 192.168.0.109:8080
              x-forwarded-proto : http
              x-forwarded-port : 8080
              x-forwarded-for : 192.168.0.109
              accept-language : zh-CN,zh;q=0.9
              accept-encoding : gzip, deflate
              referer : http://192.168.0.109:8080/index.html
              user-agent : Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36
              accept : application/json, text/plain
              connection : close
              host : 192.168.0.109:8080
        */
        changeOrigin: true  //是否改变请求头的host属性的值
      },
      '/server2': {
        target: 'http://cookie2.aaa.com:8899',
        pathRewrite:{'^/server2':''},
        //ws: true, //websocket使用
        changeOrigin: true  //是否改变请求头的host属性的值
      },
    }
  }
})
