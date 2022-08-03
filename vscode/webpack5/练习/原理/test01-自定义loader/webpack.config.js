const path = require('path')
const HtmlWebpackPlugin = require('html-webpack-plugin')
module.exports = {
    entry: './src/main.js',

    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, './dist'),
        filename: "js/[name].js",
        clean: true,
    },

    plugins: [
        /* 使用插件 */
        new HtmlWebpackPlugin({
            template: './public/index.html', //指定html模版,相对于webpack.config.js文件的路径
            filename: './index.html', //指定名字，相对于./dist目录的路径
            inject: 'body' //指定生成的<script>标签生成的位置
        })
    ],

    module: {
        rules: [
            {
                test: /\.lyq$/,
                use: ["./myloader/load1","./myloader/load2"]
            },
            {
                test: /\.lyq$/,
                use: ["./myloader/load3"]
            },
            {
                test: /\.lyq$/,
                use: ["./myloader/load4"]
            }
        ],
    },

    mode: 'development'
}