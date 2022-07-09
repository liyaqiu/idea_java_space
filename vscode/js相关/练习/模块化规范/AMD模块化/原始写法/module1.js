;(function(window){

    const data  = '我是模块1'
    function fun(){
        console.log(data)
    }

    window.module1 = {fun}
})(window)