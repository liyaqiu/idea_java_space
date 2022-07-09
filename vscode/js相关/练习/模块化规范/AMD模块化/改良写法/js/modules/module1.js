/* ;(function(window){

    const data  = '我是模块1'
    function fun(){
        console.log(data)
    }
    window.module1 = {fun} //原始暴露
})(window) */

//给模块起名mod1，以后哪里都需要用到这个名字
define('mod1',['jquery'],function(jquery){
    const data  = '我是模块1'
    function fun(){
        console.log(data)
        $('#bt1').click(function(){
            alert(2)
        })
    }
    return {fun} //AMD暴露
})