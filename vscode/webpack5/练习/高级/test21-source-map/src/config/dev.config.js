module.exports = {
    
    output: {
        /* 指定域名发布 */
        /* publicPath: 'http://192.168.0.109:8080/' */
    },

    /* 在内存中实现模块的热更新,主要注意的是没有对目标文件进行更新，只是在内存中做热更新 */
    devServer: {
        static: './dist',
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