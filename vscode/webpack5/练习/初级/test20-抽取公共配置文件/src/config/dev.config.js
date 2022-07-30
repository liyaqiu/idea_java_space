module.exports = {
    
    output: {
        /* 指定域名发布 */
        /* publicPath: 'http://192.168.0.109:8080/' */
    },

    /* 在内存中实现模块的热更新,主要注意的是没有对目标文件进行更新，只是在内存中做热更新 */
    devServer: {
        static: './dist'
    },

    mode: 'development',
    devtool: 'inline-source-map',
}