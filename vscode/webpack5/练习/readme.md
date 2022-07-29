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
    安装 npm i webpack-dev-server -D
    配置
        devServer:{
            static:'./dist'
        },
    启动 npx webpack-dev-server
    
test06-assets
    webapck提供了4种加载外部资源的方式，除了(js)外
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