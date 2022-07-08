
console.log(exports === this) //true

var moduleName = '我是模块2.2属性'
function fun(){
    console.log('我的模块2.2函数')
}

exports.moduleName = moduleName
exports.fun = fun