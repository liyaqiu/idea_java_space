export default {
    install(Vue,[x,y]){
        console.log(x,y)
        //通过插件的方式注册对象到Vue原型对象上，共享给VC实例对象使用
        Vue.prototype.config = {
            uname:'eric',
            sex:'男'
        }
    }
}