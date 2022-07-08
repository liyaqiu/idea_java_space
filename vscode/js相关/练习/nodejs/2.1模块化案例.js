

/* 
    在浏览器中的全局对象是window === globalThis === self 
    在node中，全局对象是 global === globalThis
*/
    /* a = '声明全局变量'
    fun = function(){
        console.log('声明全局函数')
    }
    global.console.log(global.a)
    global.fun() */


//每个模块的用户代码都是运行在一个函数内部，因次都有一个arguments对象， function (exports, require, module, __filename, __dirname) {}
    //console.log(arguments.callee.toString())

//1参数exports，专门用来暴露该模块的变量或者函数，也可以用module.exports 或者 this 进行暴露函数内部的变量和函数
    //需要注意的是，最后暴露出去的是这个引用，module.exports
    //console.log(exports === this) //true
    //console.log(exports === module.exports) //true
    /* exports.att1 = 'att1'
    module.exports.att2 = 'att2'
    this.fun = ()=>{
        console.log('fun')
    } */
    
//2参数require，用来引入系统内置模块和用户自定义模块
    //引入自定义模块，js后缀可以省略
        const m1 = require('./2.2模块')
        const m2 = require('./2.3模块')
        //调用模块中的属性
            //console.log(m1.moduleName)
            //console.log(m2.moduleName)
        //调用模块中的方法
            //m1.fun()
            //m2.fun()
    //引入核心模块
        const fs = require('fs')
        const path = require('path')
        //console.log(fs,path)
    //引入npm下载的包
        const m3 = require('math')
        //console.log(m3.add(100,200))

//3参数module，代表模块本身

//4参数__filename，当前文件的完整路径
    //console.log(__filename)

//5参数__dirname，当前文件的目录路径
    //console.log(__dirname)


