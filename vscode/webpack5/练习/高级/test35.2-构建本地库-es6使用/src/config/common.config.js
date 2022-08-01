const path = require('path')
//引入css合并&链接插件
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
//引入HtmlWebpackPlugin插件模块
const HtmlWebpackPlugin = require('html-webpack-plugin')
//引入yaml
const yaml = require('yaml')

const webpack = require('webpack')

module.exports = {
    /* entry: ['./src/main.js','./src/main2.js','./src/main3.js'], */
    /* entry: './src/main.js', */
    /* entry: {
        index1: ['./src/main.js','./src/main2.js'],
        index3: './src/main3.js'
    }, */
    /* entry: {
        index1: {
            import: ['./src/main.js', './src/main2.js'],
            dependOn: 'testjs'
        },
        index3: {
            import: './src/main3.js',
            filename: './app3/[name].bundle.js' //局部指定js输出位置
        },
        testjs: './src/js/test.js'
    }, */

    entry: './src/mylibrary/mylib.js',
    /* es6打包需要的 */
    experiments: {
        outputModule: true
    },
    output: {
        //全局指定js输出位置
        /* filename: 'bundle.js', */
        /* 输出多个入口文件的写法 */
        filename: 'mylib.js',
        /* 可以解决每次修改文件内容的时候被浏览器缓存的问题，增加hash值，让每次改动文件都会产生不同的hash值 */
        /* filename: '[name].[contenthash].bundle.js', */
        /* filename: './commonjs/[name].bundle.js',  */

        path: path.resolve(__dirname, '../../dist'),
        clean: true, //每次都清理dist目录

        assetModuleFilename: 'images/[contenthash][ext]', //全局指定资源加载

        
        library: {
            /* name: 'mylib', */
            type: 'module'
        }
    },

    plugins: [
        /* 使用插件 */
        new HtmlWebpackPlugin({
            title: '我的项目',
            content1: '我是css1模块的样式',
            content2: '我是css2模块的样式',
            template: './public/index.html', //指定html模版，如果不指定，则默认生成
            filename: './index.html', //指定名字
            inject: 'body', //指定生成的<script>标签生成的位置
            //chunks: ['index1','testjs'], //指定引入的js文件，默认不写则为全部引入
            /* publicPath: 'http://www.a.com' */
        }),
        /* new HtmlWebpackPlugin({
            title: '我的项目',
            content1: '我是css1模块的样式',
            content2: '我是css2模块的样式',
            template: './public/index.html', //指定html模版，如果不指定，则默认生成
            filename: './app3/index3.html', //指定名字
            inject: 'body', //指定生成的<script>标签生成的位置
            chunks: ['index3'], //指定引入的js文件，默认不写则为全部引入
            publicPath: 'http://www.b.com'
        }), */
        new MiniCssExtractPlugin({
            filename: './styles/[contenthash].css' //设置css文件保存的路径
        }),
        new webpack.ProvidePlugin(
            {
                myGlobalObj: path.resolve('./src/globaljs/myglobal.js'), //自己的全局对象
                globalObj: 'lodash' //第三方的全局对象
            }
        )
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
                test: /\.(txt|vue)$/,
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
                exclude: [/css_module/, /mode_modules/],
                use: [MiniCssExtractPlugin.loader, 'css-loader', 'postcss-loader', 'less-loader'] // css合并&链接
            },

            {
                test: /\.(css|less)$/,
                include: [/css_module/],
                use: [
                    MiniCssExtractPlugin.loader,
                    {
                        loader: 'css-loader',
                        options: {
                            modules: true //开启模块化css
                        }
                    },
                    'postcss-loader', //位css3样式添加兼容性浏览器前缀
                    'less-loader'] // css合并&链接
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
                    options: {
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
        /* 配置重复代码自动分离 */
        splitChunks: {
            /* chunks: 'all' */
            /* cacheGroups:{
                vendor:{
                    //让第三方的库缓存起来，感觉有点鸡肋
                    test: /[\\/]node_modules[\\/]/,
                    name: 'vendors',
                    chunks: 'all'
                }
            } */
        },
        usedExports: true, //摇树TreeShaking ,默认在生产模式下也会进行摇树的
    },


    resolve: {
        /* 设置别名路径 */
        alias: {
            '@js': path.resolve(__dirname, '../js'),
            '@ext': path.resolve(__dirname, '../extensions_m')
        },
        /* 设置默认读取的文件后缀顺序 */
        extensions: ['.vue', '.js', '.json']
    },

    /* 标签方式引入第三方js库 */
    externalsType: 'script', //指定生成页面的标签
    externals: {
        jqtest: ['https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.js', '$']
    }
}