const path = require('path')

//引入HtmlWebpackPlugin插件模块
const HtmlWebpackPlugin = require('html-webpack-plugin') 

module.exports = {
    entry: './src/main.js',

    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, './dist'),
        clean:true //每次都清理dist目录
    },

    /* mode: 'none', */

    /* 浏览器报错可以对应到源代码上的具体行号 */
    mode: 'development',
    devtool: 'inline-source-map', 

    plugins: [
        /* 使用插件 */
        new HtmlWebpackPlugin({
            template: './public/index.html', //指定html模版,相对于webpack.config.js文件的路径
            filename: './app.html', //指定名字，相对于./dist目录的路径
            inject: 'head' //指定生成的<script>标签生成的位置
        })
    ],
}