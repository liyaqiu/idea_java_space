module.exports = {
    
    output: {
        /* 指定域名发布 */
        /* publicPath: 'http://192.168.0.109:8080/' */
    },

    /* 
        参考官方文档
            https://webpack.docschina.org/configuration/dev-server/#devserverproxy
     */
    devServer: {
        static: './dist',
        compress: false,
        /* host:'aaa.com', */
        port: 9999,
        open: ['/app.html'],
        headers:{
            token1:'12345',
            token2:'56789'
        },
        //historyApiFallback: true, //Vue路由器模式为history模式，而不是hash模式的时候，刷新页面会有404问题，可以使用这个来解决
        //http2: true, //HTTP2 带有自签名证书
        /* https: {
            ca: './path/to/server.pem',
            pfx: './path/to/server.pfx',
            key: './path/to/server.key',
            cert: './path/to/server.crt',
            passphrase: 'webpack-dev-server',
            requestCert: true,
        }, */
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
    },
        

    mode: 'development',
    

    /*
        生产环境一般不配置sourcemap功能，防止源码泄漏，在一个就是生成环境需要压缩，减少带宽
        eval 默认，每个moudle会封装到ecal里面包裹起来执行，并且会在末尾追加//@sourceURL
        source-map 生成一个SourceMap文件
        hidden-source-map 和source-map一样，但不会在bundle末尾追加注释
        inline-source-map 生成一个DataUrl形式的SourceMap文件
        eval-source-map 每个module会通过eval()来执行，并且生成一个DataUrl形式的SourceMap
        cheap-source-map 生成一个没有列信息的SourceMap文件，不包含loader的sourcemap，比如babel的sourcemap
        cheap-module-source-map 生成一个没有列信息的SourceMap文件，同时loader的sourcemap也被简化为只包含对应的行
    */
    devtool: 'cheap-module-source-map',
}