const path = require('path')
//引入css压缩插件
const CssMinimizerWebpackPlugin = require('css-minimizer-webpack-plugin')
//引入js压缩插件
const TerserWebpackPlugin = require('terser-webpack-plugin')
//本地图片压缩
const ImageMinimizerPlugin = require("image-minimizer-webpack-plugin");

const os = require('os')

module.exports = {
    output: {
        /* 指定域名发布 */
        /* publicPath: 'http://192.168.0.109:8080/' */
    },

    /* 由于optimization配置项把默认js压缩失效了 */
    optimization: {
        minimizer: [
            new CssMinimizerWebpackPlugin(),
            new TerserWebpackPlugin({ parallel: os.cpus().length }), //本身是默认使用的，但是optimization开启以后就失效了
            /* 本地图片无损压缩，在线图片压缩不了 */
            new ImageMinimizerPlugin({
                minimizer:{
                    implementation: ImageMinimizerPlugin.imageminGenerate,
                    options:{
                        plugins: [
                            ["gifsicle", { interlaced: true }],
                            ["jpegtran", { progressive: true }],
                            ["optipng", { optimizationLevel: 5 }],
                            // Svgo configuration here https://github.com/svg/svgo#configuration
                            [
                                "svgo",
                                {
                                    plugins: [
                                        "preset-default",
                                        "prefixIds",
                                        {
                                            name: "sortAttrs",
                                            params: {
                                                xmlnsOrder: "alphabetical",
                                            }
                                        }
                                    ]
                                },
                            ],
                        ],
                    }
                }
            }),
        ],
    },

    /* 在生产变化模式下，禁用提示警告信息 */
    /* performance:{
        hints: false
    } */


    mode: 'production', //使用压缩需要改成生产环境模式
    //devtool 官方文档 https://webpack.js.org/configuration/devtool/#root
    //行列提示错误信息
    devtool: 'source-map', //一般情况下不开启

}