const path = require('path')
//引入css压缩插件
const CssMinimizerWebpackPlugin = require('css-minimizer-webpack-plugin')
//引入js压缩插件
const TerserWebpackPlugin = require('terser-webpack-plugin')
//语法检查
const ESLintPlugin = require('eslint-webpack-plugin');

module.exports = {
    output: {
        /* 指定域名发布 */
        /* publicPath: 'http://192.168.0.109:8080/' */
    },

    plugins: [
        new ESLintPlugin({
            //使用说明，前提是需要在entry引入，或者被entry入口文件关联上，其次才会根据context来进行语法检查
            context: path.resolve(__dirname, "../js_check")
        }),
    ],

    /* 由于optimization配置项把默认js压缩失效了 */
    optimization: {
        minimizer: [
            new CssMinimizerWebpackPlugin(),
            new TerserWebpackPlugin(), //本身是默认使用的，但是optimization开启以后就失效了
        ],
    },

    /* 在生产变化模式下，禁用提示警告信息 */
    /* performance:{
        hints: false
    } */

    mode: 'production', //使用压缩需要改成生产环境模式

    
}