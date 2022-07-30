const path = require('path')

//引入HtmlWebpackPlugin插件模块
const HtmlWebpackPlugin = require('html-webpack-plugin')
//引入css合并&链接插件
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
//引入css压缩插件
//const CssMinimizerWebpackPlugin = require('css-minimizer-webpack-plugin')
//引入yaml
const yaml = require('yaml')

module.exports = function(env){
    
    console.log('指定环境变量:',env)

    return {
        /* entry: './src/main.js', */
        entry:{
            index: './src/main.js',
            another: './src/main2.js',
        },
    
        output: {
            /* filename: 'bundle.js', */
            /* 输出多个入口文件的写法 */
            /* filename: '[name].bundle.js', */
            /* 可以解决每次修改文件内容的时候被浏览器缓存的问题，增加hash值，让每次改动文件都会产生不同的hash值 */
            /* filename: '[name].[contenthash].bundle.js', */
            filename: './js/[name].bundle.js',
    
            path: path.resolve(__dirname, './dist'),
            clean: true, //每次都清理dist目录
            assetModuleFilename: 'images/[contenthash][ext]', //全局指定资源加载
            
            /* 指定域名发布 */
            /* publicPath: 'http://192.168.0.109:8080/' */
        },
    
        plugins: [
            /* 使用插件 */
            new HtmlWebpackPlugin({
                template: './public/index.html', //指定html模版,相对于webpack.config.js文件的路径
                filename: './app.html', //指定名字，相对于./dist目录的路径
                inject: 'body' //指定生成的<script>标签生成的位置
            }),
            new MiniCssExtractPlugin({
                filename: './styles/[contenthash].css' //设置css文件保存的路径
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
                /* {
                    test: /\.(css|less)$/,
                    use: ['style-loader','css-loader','less-loader'] // 可以合二唯一 less和css一并处理
                }, */
                {
                    test: /\.(css|less)$/,
                    use: [MiniCssExtractPlugin.loader, 'css-loader', 'less-loader'] // css合并&链接
                },
                {
                    test: /\.(eot|ttf|woff|svg)/,
                    type: 'asset/resource',//输出文件到指定路径，并提供url访问
                    /* 局部指定资源加载 */
                    generator: {
                        filename: 'fonts/[contenthash][ext]'
                    }
                },
                {
                    test: /\.(csv|tsv)$/,
                    use: 'csv-loader'
                },
                {
                    test: /\.xml$/,
                    use: 'xml-loader'
                },
                {
                    test: /\.(yaml|yml)$/,
                    type: 'json',
                    parser: {
                        parse: yaml.parse
                    }
                },
                {
                    test: /\.js$/,
                    exclude: /node_modules/,
                    use: {
                        loader: 'babel-loader',
                        options:{
                            presets: ['@babel/preset-env'],
                            plugins: [
                                ['@babel/plugin-transform-runtime']
                            ]
                        }
                    }
                },
            ]
        },
    
        optimization: {
            /* minimizer: [
                new CssMinimizerWebpackPlugin(),
                new TerserWebpackPlugin()
            ], */
            /* 配置重复代码自动分离 */
            splitChunks:{
                chunks: 'all'
                /* cacheGroups:{
                    vendor:{
                        //让第三方的库缓存起来，感觉有点鸡肋
                        test: /[\\/]node_modules[\\/]/,
                        name: 'vendors',
                        chunks: 'all'
                    }
                } */
            },
        },
    
        /* mode: 'none', */
        /* 浏览器报错可以对应到源代码上的具体行号 */
    
        mode: 'development',
        devtool: 'inline-source-map',
    
        //mode: 'production', //使用压缩需要改成生产环境模式
    
        /* 在内存中实现模块的热更新,主要注意的是没有对目标文件进行更新，只是在内存中做热更新 */
        devServer: {
            static: './dist'
        },
    }
}