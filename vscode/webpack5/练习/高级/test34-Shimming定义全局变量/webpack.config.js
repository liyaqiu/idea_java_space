const {merge} = require('webpack-merge')
const commonConfig = require('./src/config/common.config.js')
const devConfig = require('./src/config/dev.config.js')
const proConfig = require('./src/config/pro.config.js')

module.exports = function(env){
    console.log('传入的环境变量',env)
    switch(true){
        case env.dev: 
            return merge(commonConfig,devConfig)
        case env.pro: 
            return merge(commonConfig,proConfig)
        default:
            throw new Error('配置错误！！')
    }
}


