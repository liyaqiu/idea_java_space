const path = require('path')

//引入HtmlWebpackPlugin插件模块
const HtmlWebpackPlugin = require('html-webpack-plugin')

module.exports = {
    entry: './src/main.js',

    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, './dist'),
        clean: true, //每次都清理dist目录
        assetModuleFilename: 'images/[contenthash][ext]' //全局指定资源加载
    },

    plugins: [
        /* 使用插件 */
        new HtmlWebpackPlugin({
            template: './public/index.html', //指定html模版,相对于webpack.config.js文件的路径
            filename: './app.html', //指定名字，相对于./dist目录的路径
            inject: 'body' //指定生成的<script>标签生成的位置
        })
    ],

    module: {
        rules: [
            {
                test: /\.png$/,
                type: 'asset/resource',//输出文件到指定路径，并提供url访问
                /* 局部指定资源加载 */
                generator: {
                    filename: 'images/[contenthash][ext]'
                }
            },
            {
                test: /\.jpg$/,
                type: 'asset/inline' //以base64格式编码，并且保存在bundle文件中
            },
            {
                test: /\.zip$/,
                type: 'asset', //在resource(>8k文件,找全局定义assetModuleFilename)和inline(<8k文件)种自动选择一种
                //修改默认的大小
                parser: {
                    dataUrlCondition: {
                        maxSize: 8 * 1024  //默认为8k生成url
                        //maxSize: 1024 * 1024 * 8 //改成8兆生成url
                    }
                }
            },
            {
                test: /\.txt$/,
                type: 'asset/source', //以原格式输出，并且保存在bundle文件中
            },
            /* {
                test: /\.css$/,
                use: ['style-loader','css-loader'] //利用css-loader，可以利用import引入css文件，利用style-loader，可以动态将css样式添加到<style>标签中供页面元素使用
            },
            {
                test: /\.less$/,
                use: ['style-loader','css-loader','less-loader'] // 单独处理less文件
            }, */
            {
                test: /\.(css|less)$/,
                use: ['style-loader','css-loader','less-loader'] // 可以合二唯一 less和css一并处理
            },
        ]
    },


    /* mode: 'none', */
    /* 浏览器报错可以对应到源代码上的具体行号 */
    mode: 'development',
    devtool: 'inline-source-map',

    /* 在内存中实现模块的热更新,主要注意的是没有对目标文件进行更新，只是在内存中做热更新 */
    devServer: {
        static: './dist'
    },
}