import {globalConfig,localConfig} from '../mixinjs/common-mixin.js'
export default {
    install(Vue,[x,y]){
        console.log(x,y)

        //注册全局混合属性
        Vue.mixin(globalConfig)
    }
}