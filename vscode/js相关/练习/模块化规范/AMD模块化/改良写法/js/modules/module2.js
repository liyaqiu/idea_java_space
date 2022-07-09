/* ;(function(window,module1){

    const data  = '我是模块2'

    function fun(){
        console.log(data)
        module1.fun()
    }

    window.module2 = {fun} //原始暴露
    
})(window,module1) */ //依赖的模块传入进去


//依赖的模块定义
define(['mod1','jquery'],function(mod1,jquery){
    const data  = '我是模块2'

    function fun(){
        console.log(data)
        mod1.fun()
        $('#bt2').click(function(){
            alert(3)
        })
    }

    return {fun} //AMD暴露
})