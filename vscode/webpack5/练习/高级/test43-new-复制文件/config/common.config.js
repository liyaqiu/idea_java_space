const path = require('path')
//本插件会将 CSS 提取到单独的文件中，为每个包含 CSS 的 JS 文件创建一个 CSS 文件，并且支持 CSS 和 SourceMaps 的按需加载。
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
//引入HtmlWebpackPlugin插件模块
const HtmlWebpackPlugin = require('html-webpack-plugin')
//引入yaml
const yaml = require('yaml')
//语法检查
const ESLintPlugin = require('eslint-webpack-plugin');
//引入全局对象
const webpack = require('webpack')
//拷贝文件插件
const CopyPlugin = require("copy-webpack-plugin");

const os = require('os')
console.log('CPU个数', os.cpus().length)
/* 
    相对路径，相对于指令运行的目录的路径
    npx webpack在哪里运行，那么目录就是相对于哪里
 */
module.exports = {
    /* entry: ['./src/main.js','./src/main2.js','./src/main3.js'], */
    /* entry: './src/main.js', */
    entry: {
        index1: ['./src/main.js', './src/main2.js'],
        index3: './src/main3.js',
        index4: './src/js_check/demo1.js'
    },
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


    output: {
        path: path.resolve(__dirname, '../dist'),
        clean: true, //每次都清理dist目录

        //全局指定js输出位置
        /* filename: 'bundle.js', */
        /* 输出多个入口文件的写法 */
        filename: '[name].bundle.js',
        /* 可以解决每次修改文件内容的时候被浏览器缓存的问题，增加hash值，让每次改动文件都会产生不同的hash值 */
        /* filename: '[name].[contenthash].bundle.js', */
        /* filename: './commonjs/[name].bundle.js',  */

        //动态导入命名 import(/*webpackChunkName:'test2',webpackPrefetch:true*/'@js/test2.js').then()
        chunkFilename: "dynamic/[name].chunk.js",

        // asset方式引入资源-全局指定资源加载
        assetModuleFilename: 'images/[contenthash:10][ext]',
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
            filename: 'styles/[contenthash].css', //设置css文件保存的路径
            chunkFilename: 'dynamic/[name].chunk.css',//css动态导入命名
        }),
        new webpack.ProvidePlugin(
            {
                myGlobalObj: path.resolve('./src/globaljs/myglobal.js'), //自己的全局对象
                globalObj: 'lodash' //第三方的全局对象
            }
        ),
        new ESLintPlugin({
            //使用说明，前提是需要在entry引入，或者被entry入口文件关联上，其次才会根据context来进行语法检查
            context: path.resolve("src/js_check"),
            exclude: "node_modules", //默认值
            cache: true,//开启缓存
            cacheLocation: path.resolve('../../node_modules/.cache/eslint_cache_file'), //指定缓存文件位置
            //threads: os.cpus().length,  此处开启多线程处理以后，检查语法功能失效
        }),
        new CopyPlugin({
            patterns: [
                { 
                    from: "./public", 
                    to: "./",
                    globOptions: {
                        ignore: ["**/index.html"],
                    }, 
                },
               /*  { from: "other", to: "public" }, */
            ],
        }),

    ],

    module: {
        rules: [
            {
                //每个文件只能被其中一个loader配置进行处理
                oneOf: [
                    
                    {
                        test: /\.png$/,
                        type: 'asset/resource',//输出文件到指定路径，并提供url访问
                        /* 局部指定资源加载 */
                        generator: {
                            filename: 'images/[contenthash:10][ext]'
                        }
                    },
                    {
                        test: /\.jpe?g$/,
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

                    /* 非模块化处理css */
                    {
                        test: /\.(css|less)$/,
                        exclude: [/css_module/, /mode_modules/],
                        use: [
                            MiniCssExtractPlugin.loader,
                            'css-loader',
                            {
                                loader: 'postcss-loader',
                                options: {
                                    postcssOptions: {
                                        plugins: [
                                            'postcss-preset-env', //能够解决大部分css3样式兼容性问题
                                        ],
                                    },
                                },
                            },
                            'less-loader'
                        ]
                    },
                    /* 模块化处理css */
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
                            {
                                loader: 'postcss-loader',
                                options: {
                                    postcssOptions: {
                                        plugins: [
                                            'postcss-preset-env', //能够解决大部分css3样式兼容性问题
                                        ],
                                    },
                                },
                            },
                            'less-loader'
                        ]
                    },
                    {
                        test: /\.(eot|ttf|woff?2|svg)$/,
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
                        use: [
                            {
                                loader: "thread-loader", //开启多线程
                                options: {
                                    works: os.cpus().length
                                }
                            },
                            {
                                loader: 'babel-loader',
                                options: {
                                    /* 写到了babel.config.js文件中 */
                                    /* presets: ['@babel/preset-env'],
                                    plugins: [
                                        ['@babel/plugin-transform-runtime']
                                    ] */
                                    cacheDirectory: path.resolve('../../node_modules/.cache/babel_cache_dir'), //开启babel缓存
                                    cacheCompression: false, //关闭压缩
                                }
                            }
                        ]
                    },
                ]
            }
        ]
    },

    optimization: {
        /* 
            //https://webpack.js.org/plugins/split-chunks-plugin/#optimizationsplitchunks
            配置重复代码自动分离
         */
        splitChunks: {
            chunks: 'all', //对所有模块都进行分割
            /* 
            全局配置
            minSize: 20000, //分割代码最小的值
            minRemainingSize: 0, //确保最后提取文件大小不能为0
            minChunks: 1, //至少被引用几次，满足条件才会进行代码分割
            maxAsyncRequests: 30, //按需加载时，并行加载的文件的最大数量
            maxInitialRequests: 30, //入口js文件最大并行请求数量
            enforceSizeThreshold: 50000, //超过50kb一定会单独打包(此时会忽略minRemainingSize、maxAsyncRequests、maxInitialRequests)
            cacheGroups: { //组，哪些模块要打包到一个组
                defaultVendors: { //组名，局部配置
                    test: /[\\/]node_modules[\\/]/, //匹配到的这些都要打包到一个组
                    priority: -10, //值越大权重越大
                    reuseExistingChunk: true, //如果当前chunk包含已从主bundle中拆分出模块，则它将被重用，而不是生成新的模块
                },
                default: { //默认组名，局部配置
                    minChunks: 2,
                    priority: -20,
                    reuseExistingChunk: true,
                },
            }, 
            */
        },
        usedExports: true, //摇树TreeShaking ,默认在生产模式下也会进行摇树的
    },


    resolve: {
        /* 设置别名路径 */
        alias: {
            '@js': path.resolve('src/js'),
            '@ext': path.resolve('src/extensions_m')
        },
        /* 设置默认读取的文件后缀顺序 */
        extensions: ['.vue', '.js', '.json']
    },

    /* 标签方式引入第三方js库 */
    externalsType: 'script', //指定生成页面的标签
    externals: {
        jqtest: ['https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.js', '$']
    }


    /* 禁用提示警告信息 */
    /* performance:{
        hints: false
    } */
}