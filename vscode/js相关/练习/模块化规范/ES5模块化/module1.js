;(function(window){
    let data = '我是模块化1中的数据'
    function fun(){
        console.log(data)
    }
    window.module1={
        fun
    }
})(window)