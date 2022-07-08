
console.log(exports === this) //true

var moduleName = '我是模块2.3属性'
function fun(){
    console.log('我的模块2.3函数')
}

this.moduleName = moduleName
exports.fun = fun