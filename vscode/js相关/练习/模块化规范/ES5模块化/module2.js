;(function(window){
    let data = '我是模块化2中的数据'
    function fun(){
        console.log(data)
    }
    window.module2={
        fun
    }
})(window)