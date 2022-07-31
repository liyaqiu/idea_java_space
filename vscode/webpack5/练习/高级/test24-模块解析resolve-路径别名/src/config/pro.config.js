//引入css压缩插件
const CssMinimizerWebpackPlugin = require('css-minimizer-webpack-plugin')
//引入js压缩插件
const TerserWebpackPlugin = require('terser-webpack-plugin')

module.exports = {
    output: {
        /* 指定域名发布 */
        /* publicPath: 'http://192.168.0.109:8080/' */
    },

    optimization: {
        minimizer: [
            new CssMinimizerWebpackPlugin(),
            new TerserWebpackPlugin()
        ],
    },

    /* 在生产变化模式下，禁用提示警告信息 */
    /* performance:{
        hints: false
    } */

    mode: 'production', //使用压缩需要改成生产环境模式

    
}