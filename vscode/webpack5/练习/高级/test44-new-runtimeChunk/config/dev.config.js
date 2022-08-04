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
        /* static: './public', */
        /* static: './dist',
        devMiddleware:{
            writeToDisk:true, //重新写入dist
        }, */
        //compress: false,
        /* host:'aaa.com', */
        port: 9999,
        open: true, //可以指定具体打开的页面open: ['/index.html'],
        headers:{
            token1:'12345',
            token2:'56789'
        },
        hot:false, //模块热替换
        liveReload:true,//热加载，页面时时刷新，需要把hot禁用，否则刷新不了
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
        
    },
        

    mode: 'development',
    

    /*
        devtool 官方文档 https://webpack.js.org/configuration/devtool/#root
        生产环境一般不配置sourcemap功能，防止源码泄漏，在一个就是生成环境需要压缩，减少带宽
        eval 默认，每个moudle会封装到ecal里面包裹起来执行，并且会在末尾追加//@sourceURL
        source-map 生成一个SourceMap文件
        hidden-source-map 和source-map一样，但不会在bundle末尾追加注释
        inline-source-map 生成一个DataUrl形式的SourceMap文件
        eval-source-map 每个module会通过eval()来执行，并且生成一个DataUrl形式的SourceMap
        cheap-source-map 生成一个没有列信息的SourceMap文件，不包含loader的sourcemap，比如babel的sourcemap
        cheap-module-source-map 生成一个没有列信息的SourceMap文件，同时loader的sourcemap也被简化为只包含对应的行
    */
    //行提示错误信息
    devtool: 'cheap-module-source-map',
}