;(function(window,module1){

    const data  = '我是模块2'

    function fun(){
        console.log(data)
        module1.fun()
    }

    window.module2 = {fun}
    
})(window,module1)