test02-入门使用
    在项目的根目录下创建了webpack.config.js，并且指定了打包配置
    执行打包命令
        npx webpack
        npx webpack -w/--watch 可以监测js文件发生变化实时打包

test03-html-webpack-plugin
    https://webpack.js.org/plugins/html-webpack-plugin/#installation
    安装插件 npm i  html-webpack-plugin --save-dev

test04-开发调试 
    mode: 'development', 
    devtool: 'inline-source-map', 浏览器报错可以对应到源代码上的具体行号

test05-webpack-dev-server
    在内存中实现模块的热更新,主要注意的是没有对目标文件进行更新，只是在内存中做热更新
    安装 
        npm i webpack-dev-server -D
    配置
        devServer:{
            static:'./dist'
        },
    启动 
        npx webpack-dev-server
        npx webpack-dev-server --open
    
test06-4种内置引入外部资源方式
    assetModuleFilename: 'images/[contenthash][ext]' //全局指定资源加载
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
        ]
    },
test07-引入css文件
    安装 
        npm i css-loader style-loader -D
    配置
        {
            test: /\.css$/,
            use: ['style-loader','css-loader'] //利用css-loader，可以利用import引入css文件，利用style-loader，可以动态将css样式添加到<style>标签中供页面元素使用
        },
test08-引入less文件
    安装 
        npm i less-loader less -D
    配置
        {
            test: /\.less$/,
            use: ['style-loader','css-loader','less-loader'] // 单独处理less文件
        }, 
        {
            test: /\.(css|less)$/,
            use: ['style-loader','css-loader','less-loader'] // 可以合二唯一 less和css一并处理
        },

test09-css合并&压缩&链接
    安装 
        npm i mini-css-extract-plugin -D
    引入css合并&链接插件
        const MiniCssExtractPlugin = require('mini-css-extract-plugin')
    配置
        new MiniCssExtractPlugin({
            filename: './styles/[contenthash].css' //设置css文件保存的路径
        })
        {
            test: /\.(css|less)$/,
            use: [MiniCssExtractPlugin.loader,'css-loader','less-loader'] // css合并&链接
        },
        
    安装 
        npm i css-minimizer-webpack-plugin -D
    引入css压缩插件
        const CssMinimizerWebpackPlugin = require('css-minimizer-webpack-plugin')
    配置
        optimization:{
            minimizer:[new CssMinimizerWebpackPlugin()]
        },    
        mode: 'production', //使用压缩需要改成生产环境模式

test10-引入字体图标fonts
    下载字体图标
        fonts文件夹
    在css中声明，并且声明哪个选择器进行使用
        @font-face {
            font-family: 'icomoon';
            src: url('../fonts/icomoon.eot?ej1z4w');
            src: url('../fonts/icomoon.eot?ej1z4w#iefix') format('embedded-opentype'),
                url('../fonts/icomoon.ttf?ej1z4w') format('truetype'),
                url('../fonts/icomoon.woff?ej1z4w') format('woff'),
                url('../fonts/icomoon.svg?ej1z4w#icomoon') format('svg');
            font-weight: normal;
            font-style: normal;
            font-display: block;
        }
        .box {
            font-family: 'icomoon';
            width: 100px;
            height: 100px;
            background-color: pink;
            /* background-image: url('../assets/shanshui.png'); */
        }
    配置
        {
            test: /\.(eot|ttf|woff|svg)/,
            type: 'asset/resource',//输出文件到指定路径，并提供url访问
            /* 局部指定资源加载 */
            generator: {
                filename: 'fonts/[contenthash][ext]'
            }
        },
test11-引入数据文件
    引入csv或者tsv文件，并且解析
        安装
            npm i csv-loader -D
        配置
            {
                test: /\.(csv|tsv)$/,
                use: ['csv-loader']
            },

    引入xml文件，并且解析
        安装
            npm i xml-loader -D
        配置
            {
                test: /\.xml$/,
                use: ['xml-loader']
            },
    //引入yaml或者yml，并解析
        安装
            npm i yaml -D
        引入
            const yaml = require('yaml')
        配置
            {
                test: /\.(yaml|yml)$/,
                type: 'json',
                parser: {
                    parse: yaml.parse
                }
            },

test12-js-es6转es5语法
    安装
        npm install  babel-loader @babel/core @babel/preset-env -D  可以将es6转es5
        npm i @babel/runtime  @babel/plugin-transform-runtime -D  但是需要一个函数，这个函数在这个插件里
    配置
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

test13.1-代码分离-多入口mainjs-静态导入-方式1
    此方式引入多个入口js文件，会有重复代码，每一个入口文件都会有一份
    安装:
        npm i lodash -S
    配置
        /* 引入多个入口文件 */
        entry:{
            index: './src/main.js',
            another: './src/main2.js',
        },

        output: {
            /* 输出多个入口文件的写法 */
            filename: '[name].bundle.js',
        },

test13.2-代码分离-多入口mainjs-静态导入-方式2-手动
    此方式引入多个入口js文件，不会有重复代码
    安装:
        npm i lodash -S
    配置
        entry:{
            index:{
                import: './src/main.js',
                dependOn: 'gongxiang'
            },
            another:{
                import: './src/main2.js',
                dependOn: 'gongxiang'
            },
            gongxiang: 'lodash'
        },
        filename: '[name].bundle.js',
test13.3-代码分离-多入口mainjs-静态导入--方式2-自动
    此方式引入多个入口js文件，不会有重复代码
    安装:
        npm i lodash -S
    配置
        /* 引入多个入口文件 */
        entry:{
            index: './src/main.js',
            another: './src/main2.js',
        },

        output: {
            /* 输出多个入口文件的写法 */
            filename: '[name].bundle.js',
        },
        /* 配置重复代码自动分离 */
        splitChunks:{
            chunks: 'all'
        }

test13.4-代码分离-多入口mainjs-动态导入
    此方式利用es6的动态导入来完成代码，不会有重复代码
    安装:
        npm i lodash -S
    配置
        /* 引入多个入口文件 */
        entry:{
            index: './src/main.js',
            another: './src/main2.js',
        },

        output: {
            /* 输出多个入口文件的写法 */
            filename: '[name].bundle.js',
        },
    //es6动态导入
    import('lodash').then(
        ({default:_})=>{
            console.log(_.join(['es6动态导入main2','hello','world']))
        }
    )
test13.5-代码分离-多入口mainjs-动态导入+静态导入
    此方式利用es6的动态导入来完成代码，不会有重复代码
    安装:
        npm i lodash -S
    配置
        /* 引入多个入口文件 */
        entry:{
            index: './src/main.js',
            another: './src/main2.js',
        },

        output: {
            /* 输出多个入口文件的写法 */
            filename: '[name].bundle.js',
        },
    //es6动态导入
    import('lodash').then(
        ({default:_})=>{
            console.log(_.join(['es6动态导入main2','hello','world']))
        }
    )
    //es6静态导入
    import _ from 'lodash'
    console.log(_.join(['es6静态导入main','hello','world']))
