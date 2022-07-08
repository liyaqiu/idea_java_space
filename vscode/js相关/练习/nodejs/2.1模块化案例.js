console.log('hello-world')

//自定义模块引入，js后缀可以省略
const m1 = require('./2.2模块')
const m2 = require('./2.3模块')

//调用模块中的属性
console.log(m1.moduleName)
console.log(m2.moduleName)

//调用模块中的方法
m1.fun()
m2.fun()


//核心模块引入
const fs = require('fs')
const path = require('path')
//console.log(fs,path)

//每一个模块都有一个exports对象，等同于this
//console.log(exports)

//没一个模块都有一个全局的对象global,等同于浏览器的window对象
console.log(global)